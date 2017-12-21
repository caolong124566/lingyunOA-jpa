package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.User;
import com.lingyun.framework.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index(Map<String,User> modelMap) {

        User james = initUserJames();
        modelMap.put("user",james);
        userRepository.jamesAddFirstName();
        List<User> all=userRepository.findAllUsersCustom();

        return "forward:/dist/index.html";
    }

    private User initUserJames() {
        User james=new User();
        james.setAge(23);
        james.setName("James");
        userRepository.save(james);
        return james;
    }


}
