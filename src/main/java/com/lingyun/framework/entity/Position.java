package com.lingyun.framework.entity;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {//职位表

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int posID;

    @Column(length = 30)
    private String posName;//职位名称

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "depID")
    private Department departmentID;//外键关联部门表

    public Integer getPosID() {
        return posID;
    }

    public void setPosID(Integer posID) {
        this.posID = posID;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public Department getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Department departmentID) {
        this.departmentID = departmentID;
    }

    public Position() {
        super();
    }

    public Position(String posName, Department departmentID) {
        this.posName = posName;
        this.departmentID = departmentID;
    }
}
