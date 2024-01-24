package com.sto.repository;

import com.sto.model.entity.business.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Message, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM MESSAGES WHERE CREATED_BY_ID = ?")
    List<Message> findByClientId(Long clientId);
}
