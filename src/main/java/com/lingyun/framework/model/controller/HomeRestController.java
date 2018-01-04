package com.lingyun.framework.model.controller;

<<<<<<< HEAD
import com.lingyun.framework.entity.Employee;
import com.lingyun.framework.entity.User;
import com.lingyun.framework.model.repository.EmployeeRepository;
import com.lingyun.framework.model.repository.UserRepository;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.lingyun.framework.entity.*;
import com.lingyun.framework.model.repository.*;
import com.lingyun.framework.model.service.Servicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
=======
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8

@RestController
public class HomeRestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

<<<<<<< HEAD
=======
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TaskRepositoryImpl taskRepository1;

    @Autowired
    private Servicelmpl servicelmpl;

>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8

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
    @RequestMapping("/employee")
    public List<Employee> Employee(Employee employeesi) {


        Employee employee=new Employee();
        //ID
/*        employee.setId(new Double(Math.ceil(Math.random()*100)).intValue());*/
        //电话
        employee.setTelephone("189753256"+new Double(Math.ceil(Math.random()*100)).intValue());
        //姓名
        employee.setName("Lingyun "+new Double(Math.ceil(Math.random()*100)).intValue());
        //地址
        employee.setAddress("湖南宁乡");
        //生日
        employee.setBrithday(new Date());
        //部门ID
        employee.setDepartMentId(2);
        employee.setEmail("7878787@qq.com");
        employee.setEntryDate(new Date());
        employee.setMaritalStatus("1");
        employee.setNation("汉");
        employee.setNativePlace("湖南宁乡");
        employee.setNumber("001"+new Double(Math.ceil(Math.random()*100)).intValue());
        employee.setSex("男");
        employee.setPassword("123456");
        employee.setPhotoUrl("www.www.www");
        employee.setPositionId(2);
        employee.setSalary(3000.00);
        List<Employee> employees=new ArrayList<>();
        employees.add(employee);
        employeeRepository.save(employees);
        List<Employee> all=employeeRepository.findAll();
        return all;
    }
    @RequestMapping("/session")
    public String session(HttpSession session) {
        return session.getId();
    }
<<<<<<< HEAD
=======
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

        Map<String,Object> taskMap = servicelmpl.responeTask(id);
        return taskMap;
    }

    @RequestMapping("/messageall")
    public List<MessageRespone> getMessageAll(){

        List<MessageRespone> messageAll = servicelmpl.getMessageAll(0, 5);

        return messageAll;
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
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8

}
