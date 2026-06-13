package com.Springboot.Chatbot.service.Impl;


import com.Springboot.Chatbot.dto.ChatbotResponse;
import com.Springboot.Chatbot.service.ChatbotService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatbotServiceImpl implements ChatbotService {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
            You are a helpful assistant. Format your responses in a clean,
            readable way using:
            - Clear paragraphs
            - Bullet points where needed
            - Simple and easy language
            - Proper headings if required
            Do not use unnecessary technical jargon.
            """;

    public ChatbotServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public ChatbotResponse chat(String message) {
        String response = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(message)
                .call()
                .content();
        return new ChatbotResponse(response);
    }

    @Override
    public Flux<String> chatStream(String message) {
        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(message)
                .stream()          // returns a StreamResponseSpec
                .content();        // returns Flux<String> — one chunk per token
    }
}