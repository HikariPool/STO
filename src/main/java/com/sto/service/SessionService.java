package com.sto.service;

import com.sto.model.dto.SessionDto;
import com.sto.model.entity.business.Session;
import com.sto.model.entity.business.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SessionService {
    Session load(long id);

    void create(Session session, MultipartFile image);

    List<SessionDto> getByUser(User user);

    Session getBy(Long sessionId);

    void addParticipant(Long sessionId, String email);
}
