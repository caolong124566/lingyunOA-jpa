package com.lingyun.framework.model.service;

import com.lingyun.framework.entity.*;
import com.lingyun.framework.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Servicelmpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepositoryImpl taskRepository1;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CommentRepository commentRepository;


    //通过ID 返回部门任务
        public Map<String,Object> responeTask(int id){
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


        public List<MessageRespone> getMessageAll(int page,int size){
            try {
                initMessage();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Sort.Order order = new Sort.Order(Sort.Direction.DESC,"mDate");

            Sort sort = new Sort(order);
            Pageable pageable = new PageRequest(page, size,sort);

            Page all = messageRepository.findAll(pageable);

            List<MessageRespone> messageResponeList = new ArrayList<>();

            List<Message> list = all.getContent();
            System.out.println(list);
            if(list ==null && list.size()==0){
                return null;
            }
            for (Message message: list
                 ) {
                MessageRespone messageRespone  = new MessageRespone();
                List<Comment> comments =  commentRepository.testfindByMessageID(message.getMid());
                System.out.println(comments);
                messageRespone.setMid(message.getMid());
                messageRespone.setmDate(message.getmDate());
                messageRespone.setMcontent(message.getMcontent());
                messageRespone.setEmployeeID(message.getEmployeeID().getId());
                messageRespone.setDepartmentID(message.getEmployeeID().getDepartMentId().getDepID());
                messageRespone.setDepartmentName(message.getEmployeeID().getDepartMentId().getDepName());
                messageRespone.setmComment(comments);
                messageResponeList.add(messageRespone);
            }
            return messageResponeList;
        }


    //初始化消息记录
    public void initMessage() throws ParseException {
        Employee employee = employeeRepository.findOne(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message message = new Message();
        message.setmDate(sdf.parse(sdf.format(new Date())));
        message.setEmployeeID(employee);
        message.setMcontent("这是第一条说说");
        messageRepository.save(message);

        Message message1 = new Message();
        message1.setmDate(sdf.parse(sdf.format(new Date())));
        message1.setEmployeeID(employee);
        message1.setMcontent("这是第二条说说");
        messageRepository.save(message1);

        Comment comment = new Comment();
        comment.setmDate(sdf.parse(sdf.format(new Date())));
        comment.setCcontent("这是第二条说说的评论信息1");
        Message one = messageRepository.findOne(2);
        comment.setMessageID(one);
        commentRepository.save(comment);
    }

}
