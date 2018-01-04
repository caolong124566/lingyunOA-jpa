package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.Attendance;
import com.lingyun.framework.entity.AttendanceCount;
import com.lingyun.framework.model.repository.AttendanceCountRepository;
import com.lingyun.framework.model.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lingyun.framework.entity.DateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/Attendance")
public class AttendanceRestController{

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceCountRepository attendanceCountRepository;


    /**
     * 加载打卡页面  查询用户当前日期是否已有打卡记录
     * @param request
     * @param model
     * @return Boolean bl
     */

    @RequestMapping(value="/LoadClockPage")
    public boolean LoadClockPage(HttpServletRequest request, Model model){
        boolean bl;
        //查询用户今天是否有打卡记录，传入参数为（员工编号，当前日期）
        List<Attendance> ad= attendanceRepository.byAttendanceList(Integer.parseInt(request.getParameter("employeeID")),DateUtil.ByStringday(new Date()));
        if(ad.size()>0){//为true 则有打卡记录 返回前端 上下班打卡时间 并返回false说明已有打卡记录
            bl=false;
            model.addAttribute("SignTime",ad.get(0).getSignTime());//上班打卡时间
            model.addAttribute("OutTime",ad.get(0).getOutTime());//下班打卡时间
        }
        else{
            //为false则返回true说明未有打卡记录
            bl=true;
        }
        return bl;
    }


/*    @RequestMapping(value="/findAttendance")
    public List<Attendance> findAttendance(){
        SimpleDateFormat years= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat year= new SimpleDateFormat("yyyy");
        SimpleDateFormat month= new SimpleDateFormat("MM");
        List<Attendance> atten = attendanceRepository.findAll();
        List<Date> dates = getDates(Integer.parseInt(year.format(new Date())),Integer.parseInt(month.format(new Date())));
        for(Date date : dates){
            System.out.println(years.format(date));
        }
        return atten;
}*/
    /**
     * 打卡
     * @param ad
     * @param request
     * @return Boolean bl
     */
    @RequestMapping("/ClockOn")
    public boolean ClockOn(Attendance ad, HttpServletRequest request){//使用实体（ad）接参
        Attendance attendances = new Attendance();
        String currentTime=DateUtil.ByStringHMS(new Date());//当前时分秒
        boolean bl;
        /*GregorianCalendar ca = new GregorianCalendar();
        if(ca.get(GregorianCalendar.AM_PM)==0)//判断当前时间是上午还是下午*/

        if(request.getParameter("a").equals("1")){//前端接收“a”(用于区分用户是上班打卡还是下班打卡),为1则是上班打卡
            //新增一条用户打卡记录
            try{
                attendances.setDepartmentId(Integer.parseInt(request.getParameter("DepartmentId")));//部门id
                attendances.setEmployeeId(Integer.parseInt(request.getParameter("EmployeeId")));//员工ID
                attendances.setDay(DateUtil.ByStringYear(new Date()));//当前时间（精确到年）
                attendances.setDay(DateUtil.ByStringmoth(new Date()));//当前时间（精确到月）
                attendances.setDay(DateUtil.ByStringday(new Date()));//当前时间（精确到日）
                attendances.setSignTime(currentTime);//上班打卡时间（精确到秒）
                attendanceRepository.save(attendances);

                //调用考勤统计(传入参数：打卡时间，用户ID，用以区别签到or签退【a】)
                AttendanceTJ(currentTime,Integer.parseInt(request.getParameter("EmployeeId")),1);
                bl=true;
            }
            catch (Exception e){
                bl=false;
            }
        }
        else {//不为1则是下班打卡
            try {
                //修改用户下班打卡时间，传入参数为（下班打卡时间，当前时间，员工ID）
                if(attendanceRepository.AfternoonClock(DateUtil.ByStringYMDHMS(new Date()), DateUtil.ByStringday(new Date()), ad.getEmployeeId())>0) {
                   //判断返回受影响行数是否大于0
                    //大于则打卡成功

                    //调用考勤统计(传入参数：打卡时间，用户ID，用以区别签到or签退【a】)
                    AttendanceTJ(currentTime,Integer.parseInt(request.getParameter("EmployeeId")),2);
                    bl = true;
                }
                else {
                    bl=false;
                }
            }catch(Exception e){
                bl=false;
                System.out.println(e);
            }
        }
        return bl;
    }


    /**
     * 考勤统计查询
     * @param request
     * @return
     */
    @RequestMapping(value="Statistic")
    public List<AttendanceCount> Statistic(HttpServletRequest request) throws ParseException{
        //员工ID
        int ygID=Integer.parseInt(request.getParameter("EmployeeId"));
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
    SimpleDateFormat moth = new SimpleDateFormat("MM");
    SimpleDateFormat day = new SimpleDateFormat("dd");
        String time = request.getParameter("time");
        List<Date> date= getDates(Integer.parseInt(year.format(time)),Integer.parseInt(moth.format(time)));
        List<AttendanceCount> adc =attendanceCountRepository.byList(time,ygID);
        List<Attendance> ad= attendanceRepository.byMothList(ygID,time);
        for(int i=0;i<ad.size();i++){
           if(day.format( ad.get(i).getDay())!=date.get(i).getTime()+""){
            adc.get(0).setMissWork(adc.get(0).getMissWork()+1);
           }
        }
        return adc;
    }

    //考勤统计
    public void AttendanceTJ(String currentTime,int employee,int a) throws ParseException {
        DateFormat df=new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        AttendanceCount adc = new AttendanceCount();
        adc.setField(0);
        adc.setMissCar(0);
        adc.setTimeMoth(sdf.format(currentTime));
        adc.setEmployeeId(employee);
        if(a==1){//判断是签到还是签退
            //如果是签到
            if(attendanceCountRepository.byCount(sdf.format(currentTime),employee)>0){//判断本月此员工有无月记录
                //如果有
                if(df.parse(currentTime).getTime() > df.parse("9:00:00").getTime()&&df.parse(currentTime).getTime()< df.parse("9:15:00").getTime()){
                    //超过9点 小于9点15分 记迟到一次
                    attendanceCountRepository.editRecord(0,0,1,0,sdf.format(currentTime),employee);
                }else if(df.parse(currentTime).getTime() >df.parse("9:16:00").getTime()&&df.parse(currentTime).getTime()<df.parse("10:00:00").getTime()) {
                    //超过9点15 不超过一小时 记旷工半天
                    attendanceCountRepository.editRecord(0,0,0,0.5,sdf.format(currentTime),employee);

                }else if(df.parse(currentTime).getTime()>df.parse("10:00:00").getTime()){
                    //超过一小时以上 记旷工一天
                    attendanceCountRepository.editRecord(0,0,0,1,sdf.format(currentTime),employee);
                }
            }
            else{//如果没有
                if(df.parse(currentTime).getTime() > df.parse("9:00:00").getTime()&&df.parse(currentTime).getTime()< df.parse("9:15:00").getTime()){
                    //超过9点 小于9点15分 记迟到一次
                    adc.setLate(1);
                    adc.setMissWork(0.0);
                    adc.setLeaveEarly(0);
                    attendanceCountRepository.save(adc);
                }else if(df.parse(currentTime).getTime() >df.parse("9:16:00").getTime()&&df.parse(currentTime).getTime()<df.parse("10:00:00").getTime()) {
                    //超过9点15 不超过一小时 记旷工半天
                    adc.setMissWork(0.5);
                    adc.setLate(0);
                    adc.setLeaveEarly(0);
                    attendanceCountRepository.save(adc);

                }else if(df.parse(currentTime).getTime()>df.parse("10:00:00").getTime()){
                    //超过一小时以上 记旷工一天
                    adc.setLate(0);
                    adc.setMissWork(1.0);
                    adc.setLeaveEarly(0);
                    attendanceCountRepository.save(adc);
                }

            }
        }else{
            if(attendanceCountRepository.byCount(sdf.format(currentTime),employee)>0){//判断本月此员工有无月记录
                //如果有

                if(df.parse(currentTime).getTime() > df.parse("17:15:00").getTime()&&df.parse(currentTime).getTime()< df.parse("17:30:00").getTime()){
                    //超过5:15 小于5:30 记早退一次
                    attendanceCountRepository.editRecord(0,0,1,0,sdf.format(currentTime),employee);
                }else if(df.parse(currentTime).getTime() >df.parse("16:30:00").getTime()&&df.parse(currentTime).getTime()<df.parse("17:15:00").getTime()) {
                    //早退时间为15分钟至一小时内 记旷工半天
                    attendanceCountRepository.editRecord(0,0,0,0.5,sdf.format(currentTime),employee);

                }else if(df.parse(currentTime).getTime()<df.parse("16:30:00").getTime()){
                    //早退时间为一小时以上 记旷工一天
                    attendanceCountRepository.editRecord(0,0,0,1,sdf.format(currentTime),employee);
                }
            }
            else{//如果没有
                if(df.parse(currentTime).getTime() > df.parse("17:15:00").getTime()&&df.parse(currentTime).getTime()< df.parse("17:30:00").getTime()){
                    //超过5:15 小于5:30 记早退一次
                    adc.setLeaveEarly(1);
                    adc.setLate(0);
                    adc.setMissWork(0.0);
                    attendanceCountRepository.save(adc);
                }else if(df.parse(currentTime).getTime() >df.parse("16:30:00").getTime()&&df.parse(currentTime).getTime()<df.parse("17:15:00").getTime()) {
                    //早退时间为15分钟至一小时内 记旷工半天
                    adc.setLeaveEarly(0);
                    adc.setLate(0);
                    adc.setMissWork(0.5);
                    attendanceCountRepository.save(adc);
                }else if(df.parse(currentTime).getTime()<df.parse("16:30:00").getTime()){
                    //早退时间为一小时以上 记旷工一天
                    adc.setLeaveEarly(0);
                    adc.setLate(0);
                    adc.setMissWork(1.0);
                    attendanceCountRepository.save(adc);
                }

            }

        }

    }

    /**
     * 查询指定月份工作日
     * @param year
     * @param month
     * @return
     */
    private static List<Date> getDates(int year,int month){
        List<Date> dates = new ArrayList<Date>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,  month - 1);
        cal.set(Calendar.DATE, 1);


        while(cal.get(Calendar.YEAR) == year &&
                cal.get(Calendar.MONTH) < month){
            int day = cal.get(Calendar.DAY_OF_WEEK);

            if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
                dates.add((Date)cal.getTime().clone());
            }
            cal.add(Calendar.DATE, 1);
        }
        return dates;

    }
}
