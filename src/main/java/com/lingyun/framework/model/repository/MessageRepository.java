package com.lingyun.framework.model.repository;

import com.lingyun.framework.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message,Integer>,JpaRepository<Message,Integer>{
}
