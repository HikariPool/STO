package com.sto.controller.rest;

import com.sto.model.entity.business.Message;
import com.sto.model.entity.business.User;
import com.sto.repository.ChatRepo;
import com.sto.repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private SessionRepo sessionRepo;


    @GetMapping("/get")
    public List<Message> get(@RequestParam("client_id") Long clientId) {
        return chatRepo.findByClientId(clientId);
    }

    @GetMapping("/client")
    public List<Message> get() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return chatRepo.findByClientId(currentUser.getId());
    }

    @PostMapping("/send")
    public void send(Message message) {
        chatRepo.saveAndFlush(message);
    }
}
