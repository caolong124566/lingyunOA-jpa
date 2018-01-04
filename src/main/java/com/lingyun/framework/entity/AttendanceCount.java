package com.lingyun.framework.entity;


import javax.persistence.*;

@Entity
@Table(name="AttendanceCount")
public class AttendanceCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//主键 自增 无意义
    @Column(unique = true,nullable = false)
    private Integer employeeId;//员工编号 外键
    @Column(unique = true,nullable = false)
    private Integer departmentId;//部门编号 外键
    @Column
    private Integer late;// 迟到次数
    @Column
    private Integer lTcount;//迟到分钟数
    @Column
    private Integer leaveEarly;//早退
    @Column
    private Integer lEcount;//早退分钟数
    @Column
    private Integer missCar;//缺卡
    @Column
    private Double missWork;//旷工
    @Column
    private Integer field;//外勤
    @Column
    private String timeMoth;//时间（精确到月）

    public Double getMissWork() {
        return missWork;
    }

    public void setMissWork(Double missWork) {
        this.missWork = missWork;
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

    public Integer getLate() {
        return late;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public Integer getlTcount() {
        return lTcount;
    }

    public void setlTcount(Integer lTcount) {
        this.lTcount = lTcount;
    }

    public Integer getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(Integer leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public Integer getlEcount() {
        return lEcount;
    }

    public void setlEcount(Integer lEcount) {
        this.lEcount = lEcount;
    }

    public Integer getMissCar() {
        return missCar;
    }

    public void setMissCar(Integer missCar) {
        this.missCar = missCar;
    }


    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public String getTimeMoth() {
        return timeMoth;
    }

    public void setTimeMoth(String timeMoth) {
        this.timeMoth = timeMoth;
    }

}
