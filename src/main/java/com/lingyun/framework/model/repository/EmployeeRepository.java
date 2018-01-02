package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

        @Query("SELECT e FROM  Employee e where e.telephone=?1 and e.password=?2")
        Employee getEmployeeOne(String telephone,String password);


        @Query(nativeQuery = true,value = "select * from EMPLOYEE e where e.depID = ?1")
        Employee findByEmID(Integer id);

        @Query(value = "SELECT depID FROM EMPLOYEE WHERE id = ?1",nativeQuery = true)
        Integer getDepartmentID(int id);

}
