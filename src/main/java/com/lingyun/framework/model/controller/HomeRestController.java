package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.Employee;
import com.lingyun.framework.entity.User;
import com.lingyun.framework.model.repository.EmployeeRepository;
import com.lingyun.framework.model.repository.UserRepository;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class HomeRestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


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

}
