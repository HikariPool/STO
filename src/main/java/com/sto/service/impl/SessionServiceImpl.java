package com.sto.service.impl;

import com.sto.model.dto.SessionDTO;
import com.sto.model.entity.business.Session;
import com.sto.model.entity.business.User;
import com.sto.model.entity.util.DtoConverter;
import com.sto.repository.SessionRepo;
import com.sto.repository.UserRepo;
import com.sto.service.ContentItemService;
import com.sto.service.FileService;
import com.sto.service.SessionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ContentItemService contentItemService;
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private UserRepo userRepo;


    @Override
    public Session load(long id) {
        return sessionRepo.getOne(id);
    }

    @SneakyThrows
    @Override
    public void create(Session session, MultipartFile image) {
        String fileTitle = null;
        if (image != null) {
            fileTitle = fileService.write(image.getBytes(),
                    getMemType(image.getOriginalFilename()));
        }

        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        session.setCreatedBy(user);
        session.setImagePath(fileTitle);
        List<User> users = new ArrayList<>();
        users.add(user);
        session.setParticipants(users);

        sessionRepo.save(session);

        createParticipant(session.getId(), user.getId());
    }

    private void createParticipant(Long sessionId, Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("INSERT INTO PARTICIPANTS VALUES(:user_id, :session_id)");
        query.setParameter("user_id", userId);
        query.setParameter("session_id", sessionId);
        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<SessionDTO> getByUser(User user) {
        return DtoConverter.convert(sessionRepo.getByUser(user.getId()), SessionDTO.class);
    }

    @Override
    public Session getBy(Long sessionId) {
        return sessionRepo.findById(sessionId).get();
    }

    private String getMemType(String fileTitle) {
        return fileTitle.substring(fileTitle.lastIndexOf("."));
    }

    @Override
    public void addParticipant(Long sessionId, String email) {
        User targetUser = userRepo.findByEmail(email).iterator().next();
        createParticipant(sessionId, targetUser.getId());
    }
}
