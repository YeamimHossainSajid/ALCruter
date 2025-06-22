package com.example.ChakriHub.config.ai.service;

import com.example.ChakriHub.config.ai.request.ChatRequest;
import com.example.ChakriHub.config.ai.response.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class OpenAiService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String getChatResponse(String userPrompt) {
        RestTemplate restTemplate = new RestTemplate();

        ChatRequest.Message message = new ChatRequest.Message("user", userPrompt);

        ChatRequest request = new ChatRequest("gpt-4-turbo", Collections.singletonList(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatResponse> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                entity,
                ChatResponse.class
        );

        if (response.getBody() != null && !response.getBody().getChoices().isEmpty()) {
            return response.getBody().getChoices().get(0).getMessage().getContent();
        } else {
            return "Sorry, I couldn't generate a response.";
        }
    }
}
