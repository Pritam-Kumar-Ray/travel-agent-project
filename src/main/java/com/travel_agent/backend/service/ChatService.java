package com.travel_agent.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    public String getChatResponse(String query, String conversationId) {
        // Here you would integrate with the AI chat client to get a response
        String response = chatClient.prompt()
                .user(query)
                .advisors(a-> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
        return response;
    }
}
