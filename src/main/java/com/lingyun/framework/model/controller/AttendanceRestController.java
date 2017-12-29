package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.Attendance;
import com.lingyun.framework.model.repository.AttendanceRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Attendance")
public class AttendanceRestController {


    private AttendanceRepository attendanceRepository;

    /**
     * 加载打卡页面  查询用户当前日期是否已有打卡记录
     * @param request
     * @param model
     * @return Boolean bl
     */
    @RequestMapping(value="/LoadClockPage")
    public boolean LoadClockPage(HttpServletRequest request, Model model){
        boolean bl;
        SimpleDateFormat day= new SimpleDateFormat("yyyy-MM-dd");//天
        //查询用户今天是否有打卡记录，传入参数为（员工编号，当前日期）
        List<Attendance> ad= attendanceRepository.byAttendanceList(Integer.parseInt(request.getParameter("employeeID")),day.format(new Date()));
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

    /**
     * 打卡
     * @param ad
     * @param request
     * @return Boolean bl
     */
    @RequestMapping("/ClockOn")
    public boolean ClockOn(Attendance ad, HttpServletRequest request){//使用实体（ad）接参

        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");//时分秒
        SimpleDateFormat day= new SimpleDateFormat("yyyy-MM-dd");//天
        Attendance attendances = new Attendance();
        attendances.setDepartmentId(ad.getDepartmentId());//部门id
        attendances.setEmployeeId(ad.getEmployeeId());//员工ID
        attendances.setTime(day.format(new Date()));//当前时间（精确到日）
        attendances.setSignTime(sdf.format(new Date()));//上班打卡时间（精确到秒）
        boolean bl;

        /*GregorianCalendar ca = new GregorianCalendar();
        if(ca.get(GregorianCalendar.AM_PM)==0)//判断当前时间是上午还是下午*/

        if(request.getAttribute("a").equals("1")){//前端接收“a”(用于区分用户是上班打卡还是下班打卡),为1则是上班打卡
            //新增一条用户打卡记录
            try{
                attendanceRepository.save(attendances);
                bl=true;
            }
            catch (Exception e){
                bl=false;
            }
        }
        else {//不为1则是下班打卡
            try {
                //修改用户下班打卡时间，传入参数为（下班打卡时间，当前时间，员工ID）
                if(attendanceRepository.AfternoonClock(sdf.format(new Date()), day.format(new Date()), ad.getEmployeeId())>0) {
                   //判断返回受影响行数是否大于0
                    //大于则打卡成功
                    bl = true;
                }
                else {
                    bl=false;
                }
            }catch(Exception e){
                bl=false;
            }
        }
        return bl;
    }

}
