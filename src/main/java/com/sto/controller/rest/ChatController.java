package com.sto.controller.rest;

import com.sto.model.entity.business.Chat;
import com.sto.model.entity.business.Message;
import com.sto.service.impl.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatServiceImpl chatService;


    @GetMapping("/get/{chatId}")
    public Chat get(@PathVariable Long chatId) {
        return chatService.getChatById(chatId);
    }

    @GetMapping("/all")
    public List<Chat> getChats() {
        return chatService.getChats();
    }

    @PostMapping("/send/{chatId}")
    public void send(@PathVariable Long chatId, Message message) {
        chatService.send(chatId, message);
    }
}
