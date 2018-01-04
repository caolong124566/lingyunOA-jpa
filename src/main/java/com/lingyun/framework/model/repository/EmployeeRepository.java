package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Employee;
<<<<<<< HEAD
import com.lingyun.framework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

    /**
     * 登陆
     * @param telephone
     * @param password
     * @return List<Employee>
     */
    @Query("select u from Employee u where telephone=?1 and password=?2")
     List<Employee> testgetListEmployee(String telephone,String password);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

        @Query("SELECT e FROM  Employee e where e.telephone=?1 and e.password=?2")
        Employee getEmployeeOne(String telephone,String password);


        @Query(nativeQuery = true,value = "select * from EMPLOYEE e where e.depID = ?1")
        Employee findByEmID(Integer id);

        @Query(value = "SELECT depID FROM EMPLOYEE WHERE id = ?1",nativeQuery = true)
        Integer getDepartmentID(int id);
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8

}
