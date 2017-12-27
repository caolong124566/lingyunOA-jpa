package com.lingyun.framework.model.controller;

import com.lingyun.framework.entity.Employee;
import com.lingyun.framework.entity.User;
import com.lingyun.framework.model.repository.EmployeeRepository;
import com.lingyun.framework.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MemberRepository memberRepository;

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
    @RequestMapping("/session")
    public String session(HttpSession session) {


        return session.getId();
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

    public Employee initEmployee() throws ParseException {
        Employee e = new Employee();
        int random = (int) Math.random()*100;
        e.setTelephone("18229804728"+random);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        e.setBrithday(sdf.parse(sdf.format(new Date())));
        e.setBrithday(sdf.parse(sdf.format(new Date())));
        e.setEmail("674670072@qq.com");
        e.setEntryDate(sdf.parse(sdf.format(new Date())));
        e.setAddress("宁乡");
        e.setName("贤哥");
        e.setNumber("007"+random);
        e.setNation("汉族");
        e.setNativePlace("邵阳");
        e.setPhotoUrl("c:");
        e.setDepartMentId(1);
        e.setPositionId(1);
        e.setSalary(22.00);
        e.setMaritalStatus(1);
        e.setPassword("123456");
        e.setSex("男");
        return e;
    }


    @RequestMapping("/members")
    public List<Member> getMembers(){
        List<Member> all = memberRepository.addmem();
        return all;
    }

    @RequestMapping("/addmember")
    public Member addMember(){

        int random = (int) (Math.random()*100);
        String userid = "abc"+random;
        Member m = new Member(userid,"123456");
        Member save = memberRepository.save(m);

//        myRepository.sharedCustomMethod("2");
        return save;

    }


}
