package com.Springboot.Chatbot.service;



import com.Springboot.Chatbot.dto.ChatbotResponse;
import reactor.core.publisher.Flux;

public interface ChatbotService {
    ChatbotResponse chat(String message);         // existing
    Flux<String> chatStream(String message);      // new streaming method
}
