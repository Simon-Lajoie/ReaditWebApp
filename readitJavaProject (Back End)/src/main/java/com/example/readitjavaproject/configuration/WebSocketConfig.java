package com.example.readitjavaproject.configuration;

import com.example.readitjavaproject.controller.ChatController;
import com.example.readitjavaproject.service.impl.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final UserService userService;

    public WebSocketConfig(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatController(userService), "/chat").setAllowedOrigins("*");
    }

}