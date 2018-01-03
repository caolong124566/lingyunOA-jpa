package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

        @Query(value = "SELECT cid,ccontent FROM Comment c WHERE mid = ?1",nativeQuery = true)
        List<Comment> testfindByMessageID(int mid);
}
