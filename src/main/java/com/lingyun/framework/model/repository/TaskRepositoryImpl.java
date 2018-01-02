package com.lingyun.framework.model.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

public class TaskRepositoryImpl {

    @PersistenceContext()
    private EntityManager em;
    //通过部门ID 查询部门关联的任务ID
    public List<Integer> getTaskID(int did){
        String SQL = "select T_ID from TASK_ENTITY where DEPID=?1";
        List resultList = em.createNativeQuery(SQL).setParameter(1, did).getResultList();
        return resultList;
    }
}
