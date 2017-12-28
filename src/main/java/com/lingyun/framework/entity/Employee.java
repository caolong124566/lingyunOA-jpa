package com.lingyun.framework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//主键   无意义
    @Column(length = 50,unique = true,nullable = false)
    private String number;//员工编号 唯一值
    @Column(length = 20,nullable = false,unique = true)
    private String telephone;//注册时的手机号码 唯一值
    @Column(length = 30,nullable = false)
    private String name;//用户昵称
    @Column(length = 16,nullable = false,columnDefinition = "varchar(16) default '123456'")
    private String password;//用户密码
    @Column(length = 1,nullable = false,columnDefinition = "varchar(1) default '男'")
    private String sex;//用户性别  默认为男
    @Column(length = 30,nullable = false)
    private Date brithday;//出生年月日
    @Column(length = 10,nullable = false)
    private Integer positionId;//职位ID  为外键
    @Column(length = 30,nullable = false)
    private Integer departMentId;//部门ID  为外键
    @Column(length = 20,nullable = false)
    private String nation;//民族 不能为空
    @Column(length = 20,nullable = false)
    private String nativePlace;//籍贯 不能为空
    @Column(length = 20,nullable = false)
    private Double salary;//工资  不能为空
    @Column(length = 50)
    private String address;//员工地址   可以为空
    @Column(length = 50)
    private String email;//员工邮箱  可以为空
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date entryDate;//入职时间 不能为空
    @Column(nullable = false)
    private String maritalStatus;//婚姻状况ID 外键
    @Column(nullable = false)
    private String photoUrl;//照片地址


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getDepartMentId() {
        return departMentId;
    }

    public void setDepartMentId(Integer departMentId) {
        this.departMentId = departMentId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }



}
