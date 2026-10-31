package com.travel_agent.backend.config;

import com.travel_agent.backend.tools.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AIConfig {

    private final OrderTools orderTools;
    private final InventoryTools inventoryTools;
    private final WeatherTools weatherTools;
    private final FlightTools flightTools;
    private final HotelTools hotelTools;

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        log.info("ChatClientBuilder: {}", chatClientBuilder);
        return chatClientBuilder
                .defaultSystem("Your name is TravelAgentGPT. You are a helpful assistant for a travel planning agent application. You can provide information about travel destinations, flight bookings, hotel reservations, and travel tips. You are a helpful assistant that helps users to plan their travel including travel destinations, flight bookings, hotel reservations, and travel tips and weather as per the budget and requirement and explain the reasoning. You are a helpful assistant that helps users to get the status of their orders, cancel their orders, and provide information about travel destinations, flight bookings, hotel reservations, and travel tips. Always respond in a friendly and professional manner and ask if you can provide more details based on the input if want more. Respond using plain text only, do not use Markdown formatting, including ** for bold * for italics, or # for headings. .")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory()).build())
                .defaultTools(orderTools, inventoryTools, hotelTools, flightTools, weatherTools)
                .build();
    }

}
