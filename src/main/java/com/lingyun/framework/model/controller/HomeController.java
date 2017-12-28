package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.Employee;
import com.lingyun.framework.entity.User;
import com.lingyun.framework.model.repository.EmployeeRepository;
import com.lingyun.framework.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @RequestMapping("/")
    public String index(Map<String,User> modelMap) {

        User james = initUserJames();
        modelMap.put("user",james);
        userRepository.jamesAddFirstName();
        List<User> all=userRepository.findAllUsersCustom();

        return "index";
    }
    @RequestMapping("/angularUi")
    public String angularUi(){
        return "forward:index.html";
    }
    private User initUserJames() {
        User james=new User();
        james.setAge(23);
        james.setName("James");
        userRepository.save(james);
        return james;
    }
    @RequestMapping("/login")
    public String login(HttpSession session, Employee employee){
        String page="";
        List<Employee> employees=employeeRepository.testgetListEmployee(employee.getTelephone(),employee.getPassword());
        if(employees.size()>0){
            page="home";
            session.setAttribute("id",employees.get(0).getId());
        }
        else{
            page="index";

        }
        return page;
    }
}
