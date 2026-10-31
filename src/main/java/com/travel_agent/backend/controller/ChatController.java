package com.travel_agent.backend.controller;

import com.travel_agent.backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<String> chat(@RequestHeader("Conversation-Id") String conversationId, @RequestBody String message){
        String response = chatService.getChatResponse(message, conversationId);
        return ResponseEntity.ok(response);
    }
}
