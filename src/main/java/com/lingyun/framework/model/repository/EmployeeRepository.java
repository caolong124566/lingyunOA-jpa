package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Employee;
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

}
