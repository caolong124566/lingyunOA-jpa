package com.lingyun.framework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
    @Column
    private Integer employeeId;
    @Column
    private Integer departmentId;
    @Column
    private String signTime;
    @Column
    private String outTime;
    @Column
    private String time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




}
