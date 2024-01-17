package com.flat.service;

import com.flat.model.dto.SessionDTO;
import com.flat.model.entity.business.Session;
import com.flat.model.entity.business.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SessionService {
    Session load(long id);

    void create(Session session, MultipartFile image);

    List<SessionDTO> getByUser(User user);

    Session getBy(Long sessionId);

    void addParticipant(Long sessionId, String email);
}
