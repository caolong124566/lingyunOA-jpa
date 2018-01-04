package com.lingyun.framework.entity;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {//部门表

    @Id
    @GeneratedValue
    private int depID;//主键   无意义

    @Column(length = 6,name = "depNumber")
    private String depNumber;//部门编号

    @Column(length = 30,name = "depName")
    private String depName;//部门名字

    public Department() {
        super();
    }

    public Integer getDepID() {
        return depID;
    }

    public void setDepID(Integer depID) {
        this.depID = depID;
    }

    public String getDepNumber() {
        return depNumber;
    }

    public void setDepNumber(String depNumber) {
        this.depNumber = depNumber;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depID=" + depID +
                ", depNumber='" + depNumber + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }

    public Department(String depNumber, String depName) {
        this.depNumber = depNumber;
        this.depName = depName;
    }
}
