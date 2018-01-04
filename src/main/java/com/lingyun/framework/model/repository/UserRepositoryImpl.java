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
<<<<<<< HEAD
}
=======
    }


//    public List<Member> getMember(){
//        String SQL = "";
//        return null;
//    }
>>>>>>> 3d46f6cd65bd57d1e198eefc4d9f71d5547b43d8
}
