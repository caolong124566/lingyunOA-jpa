package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.*;
import com.lingyun.framework.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class HomeRestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TaskRepositoryImpl taskRepository1;


    @RequestMapping("/users")
    public List<User> users(HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        User user=new User();
        user.setAge(new Double(Math.ceil(Math.random()*100)).intValue());
        user.setName("Lingyun "+new Double(Math.ceil(Math.random()*100)).intValue());
        List<User> users=new ArrayList<>();
        users.add(user);
        userRepository.save(users);
        List<User> all=userRepository.findAll();

        return all;
    }
    @RequestMapping("/session")
    public String session(HttpSession session) {


        return session.getId();
    }
    //前端提供用户id 返回用户所在部门的任务列表
    @RequestMapping("/task/{id}")
    public Map<String,Object> respTask(@PathVariable("id")Integer id) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(sdf.format(new Date()));
        Department department = departmentRepository.findOne(id);

        List<Department> list =  new ArrayList<>();
        list.add(department);

        Task t = new Task("开会","开开开",d);
        Task t1 = new Task("开会1","开开开1",d);
        t.setDepartments(list);
        t1.setDepartments(list);

        Employee employee = employeeRepository.findByEmID(id+1);

        t.setEmployeeID(employee);
        t1.setEmployeeID(employee);
        taskRepository.save(t);
        taskRepository.save(t1);

        Integer departmentID = employeeRepository.getDepartmentID(id);

        System.out.println(departmentID+"*&^&^*^&^(");
        Map<String,Object> taskMap = new HashMap<>();
        //存储任务实体
        List<PhysicalTask> taskList = new ArrayList<>();
        List<Integer> tasks = taskRepository1.getTaskID(departmentID);
        if(tasks.size()==0 || tasks == null){
            taskMap.put("taskStatus",0);
            taskMap.put("msg","该部门暂时没有任务。。。");
            return taskMap;
        }
        System.out.println(tasks.size()+"****");
        for (int ii: tasks
                ) {
            Task one = taskRepository.findOne(ii);
            if(one==null){
                taskMap.put("taskStatus",0);
                return taskMap;
            }
            taskMap.put("taskStatus",1);

            PhysicalTask physicalTask = new PhysicalTask();
            physicalTask.setT_id(one.getT_id());
            physicalTask.setT_theme(one.getT_theme());
            physicalTask.setEmployeeID(one.getEmployeeID().getId());
            physicalTask.setT_content(one.getT_content());
            physicalTask.setT_dateTime(one.getT_dateTime());
            physicalTask.setEmployeeName(one.getEmployeeID().getName());
            taskList.add(physicalTask);
        }
        taskMap.put("task",taskList);
        return taskMap;
    }


    @RequestMapping("/employee")
    public Map<String,Object> getEmployee(HttpServletRequest req,String telephone, String password) throws ParseException {
        System.out.println(telephone+"=="+password);
        HashMap<String,Object> hm = new HashMap<>();
        Employee em = initEmployee();
        employeeRepository.save(em);
        Employee employee = (Employee) employeeRepository.getEmployeeOne(telephone, password);
        if(employee==null){
            hm.put("S",0);
            hm.put("login","请输入正确的账号密码。。。");
        }else{
            hm.put("S",1);
            hm.put("login","账号密码输入正确！");
            req.getSession().setAttribute("telephone",employee.getTelephone());
            req.getSession().setAttribute("password",employee.getPassword());
            hm.put("userid",employee.getId());
        }
        return hm;
    }
    //初始化一个用户基本信息
    public Employee initEmployee() throws ParseException {
        Employee e = new Employee();
        int random = (int) Math.random()*100;
        e.setTelephone("18229804728");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        e.setBrithday(sdf.parse(sdf.format(new Date())));
        e.setBrithday(sdf.parse(sdf.format(new Date())));
        e.setEmail("674670072@qq.com");
        e.setEntryDate(sdf.parse(sdf.format(new Date())));
        e.setAddress("宁乡");
        e.setName("贤哥");
        e.setNumber("007");
        e.setNation("汉族");
        e.setNativePlace("邵阳");
        e.setPhotoUrl("c:");
        Department d = new Department("001","技术部");
        e.setDepartMentId(d);
        Position p = new Position("客服",d);
        e.setPositionId(p);
        e.setSalary(22.00);
        e.setMaritalStatus(1);
        e.setPassword("123456");
        e.setSex("男");
        return e;
    }

}
