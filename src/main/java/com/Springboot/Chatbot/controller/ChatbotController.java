package com.Springboot.Chatbot.controller;



import com.Springboot.Chatbot.dto.ChatbotResponse;
import com.Springboot.Chatbot.service.ChatbotService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")   // adjust for your frontend origin
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    // Existing non-streaming endpoint
    @PostMapping
    public ChatbotResponse chat(@RequestParam String message) {
        return chatbotService.chat(message);
    }

    // New streaming endpoint — returns Server-Sent Events (SSE)
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam String message) {
        return chatbotService.chatStream(message);
    }
}
