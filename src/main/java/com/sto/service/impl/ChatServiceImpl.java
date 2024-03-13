package com.sto.service.impl;

import com.sto.data.constants.Constants;
import com.sto.model.entity.business.Chat;
import com.sto.model.entity.business.Message;
import com.sto.model.entity.business.User;
import com.sto.repository.ChatRepo;
import com.sto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UserService userService;


    public List<Chat> getChats() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<Chat> chats = chatRepo.findUserChats(currentUser.getId());
        if ((chats == null || chats.isEmpty()) && !Constants.ADMIN.equals(currentUser.getRole())) {
            Chat chat = Chat.builder()
                    .targetUser(userService.getAdmin())
                    .build();
            chatRepo.saveAndFlush(chat);
            chats = Collections.singletonList(chat);
        }
        return chats;
    }

    public Chat getChatById(Long chatId) {
        return chatRepo.findById(chatId).get();
    }

    public Chat getOrCreateChat() {
        Chat chat = Chat.builder()
                .targetUser(userService.getAdmin())
                .build();
        return chatRepo.saveAndFlush(chat);
    }

    public void send(Long chatId, Message message) {
        Optional<Chat> chatOpt = chatRepo.findById(chatId);

        if (chatOpt.isPresent()) {
            Chat chat = chatOpt.get();
            if (chat.getMessages() != null) {
                chat.getMessages().add(message);
            } else {
                chat.setMessages(Collections.singletonList(message));
            }
            chatRepo.saveAndFlush(chat);
        }
    }
}
