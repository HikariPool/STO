package com.sto.repository;

import com.sto.model.entity.business.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM chats WHERE created_by_id = ?1 OR target_user_id = ?1")
    List<Chat> findUserChats(Long userId);
}
