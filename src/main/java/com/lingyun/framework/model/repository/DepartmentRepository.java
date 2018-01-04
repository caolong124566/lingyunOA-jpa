package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {


}
