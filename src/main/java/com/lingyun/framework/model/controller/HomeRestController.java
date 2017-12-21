package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.User;
import com.lingyun.framework.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HomeRestController {
    @Autowired
    private UserRepository userRepository;

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
}
