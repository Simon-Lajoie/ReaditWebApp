package com.example.readitjavaproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.readitjavaproject.domain.ChatMessage;
import com.example.readitjavaproject.service.DTO.SmallUserDTO;
import com.example.readitjavaproject.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class ChatController extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions = new ArrayList<>();
    private final UserService userService;

    @Autowired
    public ChatController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String username = session.getUri().getQuery().split("=")[1];
        String messagePayload = message.getPayload().replaceAll("\\\\(.)", "$1");
        ChatMessage chatMessage = new ChatMessage(username, messagePayload.replaceAll("^\"|\"$", ""));
        ObjectMapper mapper = new ObjectMapper();
        String jsonMessage = mapper.writeValueAsString(chatMessage);
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(jsonMessage));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        String username = session.getUri().getQuery().split("=")[1];
        ChatMessage chatMessage = new ChatMessage(null, username + " has joined the chat room.");
        ObjectMapper mapper = new ObjectMapper();
        String jsonMessage = mapper.writeValueAsString(chatMessage);
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(jsonMessage));
        }
        Optional<SmallUserDTO> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            userService.setUserOnline(user.get(), user.get().getId(), true);
        }
        //System.out.println("connected to websocket successfully: " + username);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        String username = session.getUri().getQuery().split("=")[1];
        ChatMessage chatMessage = new ChatMessage(username, username + " has left the chat room.");
        ObjectMapper mapper = new ObjectMapper();
        String jsonMessage = mapper.writeValueAsString(chatMessage);
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(jsonMessage));
        }
        Optional<SmallUserDTO> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            userService.setUserOnline(user.get(), user.get().getId(), false);
        }
    }
}
