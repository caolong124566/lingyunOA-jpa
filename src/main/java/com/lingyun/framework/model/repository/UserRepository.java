package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    List<User> findByName(String name);
    @Query("select u from User u")
    List<User> findAllUsersCustom();
    @Transactional
    int jamesAddFirstName();
}
