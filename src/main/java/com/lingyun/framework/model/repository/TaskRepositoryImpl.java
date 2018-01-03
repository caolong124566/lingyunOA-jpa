package com.lingyun.framework.model.repository;


import com.lingyun.framework.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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


    public List<Comment> test(int mid){
        String sql = "select new com.lingyun.framework.entity.Comment(p.cid,p.ccontent,p.mDate) from Comment p where p.messageID=?1";
        TypedQuery<Comment> query = em.createQuery(sql, Comment.class);
        query.setParameter(1,mid);
        List<Comment> result = query.getResultList();
        return result;
    }

}
