package com.sto.controller.rest;

import com.sto.model.dto.SessionDTO;
import com.sto.model.entity.business.User;
import com.sto.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;


    @PostMapping("/create")
    public void create(@RequestParam(value = "img", required = false) MultipartFile image, SessionDTO sessionDTO) {
        String title = sessionDTO.getTitle();
        if (title != null && !title.isEmpty()) {
            sessionService.create(SessionDTO.convertToEntity(sessionDTO), image);
            return;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Title is empty!");
    }

    @GetMapping("/all")
    public List<SessionDTO> getAll() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return sessionService.getByUser(currentUser);
    }

    @GetMapping("/get")
    public SessionDTO getSessionBy(@RequestParam("session_id") Long sessionId) {
        return SessionDTO.convertToDto(sessionService.getBy(sessionId));
    }

    @PostMapping("/people")
    public void addPeolple(@RequestParam("session_id") Long sessionId, @RequestParam("email") String email) {
        sessionService.addParticipant(sessionId, email);
    }
}
