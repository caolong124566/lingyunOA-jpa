package com.lingyun.framework.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl {
    @PersistenceContext
    private EntityManager em;
    public int jamesAddFirstName(){
        String update="update User u set u.name='George James' where u.name='James'";
        System.out.println(update);
        return em.createQuery(update).executeUpdate();
}
}
