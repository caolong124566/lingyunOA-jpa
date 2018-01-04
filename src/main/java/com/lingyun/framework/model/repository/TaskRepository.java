package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ManyToMany;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    @Query(nativeQuery = true,value = "select * from task t where t.t_id=:id")
    Task findOne(@Param("id") Integer id);

}
