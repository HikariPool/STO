package com.sto.controller.rest;

import com.sto.config.security.service.AccessibilityServiceImpl;
import com.sto.model.entity.business.Chat;
import com.sto.model.entity.business.Message;
import com.sto.model.entity.business.User;
import com.sto.repository.ChatRepo;
import com.sto.repository.UserRepo;
import com.sto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccessibilityServiceImpl accessibilityService;


    @GetMapping("/get")
    public List<Message> get(@RequestParam("client_id") Long clientId) {
        return chatRepo.findByCreatedBy(userRepo.findById(clientId).get()).getMessages();
    }

    @GetMapping("/client")
    public List<Message> get() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Chat chat = chatRepo.findByCreatedBy(currentUser);
        return chat != null ? chat.getMessages() : Collections.emptyList();
    }

    @PostMapping("/send")
    public void send(Message message) {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Chat chat = chatRepo.findByCreatedBy(currentUser);
        if (chat != null) {
            chat.getMessages().add(message);
        } else {
            User admin = userService.getAdmin();
            chat = Chat.builder()
                    .targetUser(admin)
                    .messages(Collections.singletonList(message))
                    .build();
        }
        chatRepo.saveAndFlush(chat);
    }

    @PostMapping("/admin/send")
    public void send(@RequestParam(value = "client_id") Long clientId, Message message) {
        if (accessibilityService.currentUserHasAccess()) {

        }
    }

    @GetMapping("/getChats")
    public List<Chat> getChats() {
        if (accessibilityService.currentUserHasAccess()) {
            return chatRepo.findAll();
        }
        return null;
    }
}
