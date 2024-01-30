package com.sto.repository;

import com.sto.model.entity.business.Chat;
import com.sto.model.entity.business.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {

    Chat findByCreatedBy(User user);

    Chat findByTargetUser(User user);
}
