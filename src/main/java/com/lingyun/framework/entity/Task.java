package com.lingyun.framework.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {//任务表
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int t_id;

    @Column(name = "t_theme",length = 100)//任务标题
    private String t_theme;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Employee employeeID;

    //@Column(length = 255)//任务内容
    private String t_content;

    @Temporal(TemporalType.TIMESTAMP)//任务发布时间
    @Column(name = "t_dateTime")
    private Date t_dateTime;

    @ManyToMany
    @JoinTable(name = "taskEntity"
            ,joinColumns = {@JoinColumn(name = "t_id")},
    inverseJoinColumns = {@JoinColumn(name = "depID")})
    private List<Department> departments;

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_theme() {
        return t_theme;
    }

    public void setT_theme(String t_theme) {
        this.t_theme = t_theme;
    }

    public String getT_content() {
        return t_content;
    }

    public void setT_content(String t_content) {
        this.t_content = t_content;
    }

    public Date getT_dateTime() {
        return t_dateTime;
    }

    public void setT_dateTime(Date t_dateTime) {
        this.t_dateTime = t_dateTime;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public Task() {
        super();
    }



    @Override
    public String toString() {
        return "Task{" +
                "t_id=" + t_id +
                ", t_theme='" + t_theme + '\'' +
                ", t_content='" + t_content + '\'' +
                ", t_dateTime=" + t_dateTime +
                '}';
    }

    public Task(String t_theme, String t_content, Date t_dateTime) {
        this.t_theme = t_theme;
        this.t_content = t_content;
        this.t_dateTime = t_dateTime;
    }

}
