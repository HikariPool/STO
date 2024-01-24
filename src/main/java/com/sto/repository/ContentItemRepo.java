package com.sto.repository;

import com.sto.model.entity.business.ContentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContentItemRepo extends JpaRepository<ContentItem, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM content_items WHERE session_id = ? ORDER BY item_order")
    List<ContentItem> getBy(Long sessionId);

    @Query(nativeQuery = true, value = "delete from content_items where id = ?")
    @Transactional
    @Modifying
    void delete(Long id);

    @Query(nativeQuery = true, value = "delete from content_items where session_id = ?")
    @Transactional
    @Modifying
    void deleteBySessionId(Long id);
}
