package com.example.chat.controller;

import com.example.chat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

/*    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }*/

    @MessageMapping("/chat.sendMessage")
    //@SendToUser("/queue/chat")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSendToUser("user", "/queue/chat", chatMessage);
        return chatMessage;
    }



    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
/*        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println(user);*/
        //headerAccessor.getSessionAttributes().put("username", "Olga");
        return chatMessage;
    }
}
