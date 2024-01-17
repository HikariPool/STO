package com.flat.repository;

import com.flat.model.entity.business.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM Sessions WHERE id IN(SELECT session_id FROM Participants WHERE user_id = :userId)")
    List<Session> getByUser(Long userId);

    @Query(nativeQuery = true,
            value = "delete from sessions where id = ?")
    @Transactional
    @Modifying
    void delete(Long id);

    @Query(nativeQuery = true, value = "delete from participants where session_id = ?")
    @Transactional
    @Modifying
    void deleteParticipantsBySessionId(Long id);
}
