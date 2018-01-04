package com.lingyun.framework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//主键   无意义
    @Column(length = 50,nullable = false)
    private String number;//员工编号 唯一值


    @Column(length = 20,nullable = false)
    private String telephone;//注册时的手机号码 唯一值
    @Column(length = 30,nullable = false)
    private String name;//用户昵称
    @Column(length = 16,nullable = false,columnDefinition = "varchar(16) default '123456'")
    private String password;//用户密码
    @Column(length = 1,nullable = false,columnDefinition = "varchar(1) default '男'")
    private String sex;//用户性别  默认为男
    @Column(length = 30,nullable = false)
    private Date brithday;//出生年月日

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posID")
    private Position positionId;//职位ID  为外键
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "depID")
    private Department departMentId;//部门ID  为外键

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
    private Integer maritalStatus;//婚姻状况ID 外键
    @Column(nullable = false)
    private String photoUrl;//照片地址

    public Employee() {
        super();
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    public Department getDepartMentId() {
        return departMentId;
    }

    public void setDepartMentId(Department departMentId) {
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

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", brithday=" + brithday +
                ", positionId=" + positionId +
                ", departMentId=" + departMentId +
                ", nation='" + nation + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", entryDate=" + entryDate +
                ", maritalStatus=" + maritalStatus +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
