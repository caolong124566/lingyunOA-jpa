package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.Employee;
import com.lingyun.framework.entity.Message;
import com.lingyun.framework.entity.User;
import com.lingyun.framework.model.repository.EmployeeRepository;
import com.lingyun.framework.model.repository.UserRepository;
import com.lingyun.framework.model.service.Servicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController{
    @Autowired
    private UserRepository userRepository;

    @Autowired

    private EmployeeRepository employeeRepository;

    private Servicelmpl servicelmpl;



    @RequestMapping("/")
    public String index(Map<String,User> modelMap) {
        User james = initUserJames();
        modelMap.put("user",james);
        userRepository.jamesAddFirstName();
        List<User> all=userRepository.findAllUsersCustom();


        return "forward:/index.html";
    }
    /**
     * 登陆
     * @param session
     * @param employee
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session, Employee employee){
        String page="";
        List<Employee> employees=employeeRepository.testgetListEmployee(employee.getTelephone(),employee.getPassword());
        if(employees.size()>0){
            page="home";
            session.setAttribute("id",employees.get(0).getId());
        }
        else{
            page="home";

        }
        return page;
    }

    /**
     * angularUI
     * @return
     */
    @RequestMapping("/angularUi")
    public String angularUi(){
        return "forward:/index.html";
    }

    @RequestMapping("/attendance")
    public String attendance(HttpSession session){



        return "forward:/dist/index.html";
    }

    @RequestMapping("/user")
    public String index1(Map<String,User> modelMap) {
        User james = initUserJames();
        modelMap.put("user",james);
        userRepository.jamesAddFirstName();
        List<User> all=userRepository.findAllUsersCustom();
        System.out.println(all);
        return "forward:/angular/dist/index.html";
    }

    private User initUserJames() {
        User james=new User();
        james.setAge(23);
        james.setName("James");
        userRepository.save(james);
        return james;
    }


}
