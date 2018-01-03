package com.lingyun.framework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;//主键 唯一键
    @Column
    private Integer employeeId;//外键 关联员工表
    @Column
    private Integer departmentId;//外键  关联部门表
    @Column
    private String signTime;//上班打卡（精确到秒）
    @Column
    private String outTime; //下班打卡（精确到秒）
    @Column
    private String year; //时间（精确到年）
    @Column
    private String moth; //时间（精确到月）
    @Column
    private String day; //时间（精确到天）



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMoth() {
        return moth;
    }

    public void setMoth(String moth) {
        this.moth = moth;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }



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

}
