package com.lingyun.framework.entity;

import javax.persistence.*;
import java.util.Date;

public class PhysicalTask {

    private int t_id;

    private String t_theme;

    private int employeeID;

    private String employeeName;

    private String t_content;

    private Date t_dateTime;

    public PhysicalTask() {
        super();
    }

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

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setT_dateTime(Date t_dateTime) {
        this.t_dateTime = t_dateTime;
    }


}
