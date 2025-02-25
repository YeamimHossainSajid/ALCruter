package com.example.ChakriHub.config.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class OpenRouterService {

    @Value("${openrouter.api.key}")
    private String openRouterApiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OpenRouterService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getChatCompletion(String userMessage) {
        String response;

        try {
            String url = "https://openrouter.ai/api/v1/chat/completions";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + openRouterApiKey);
            headers.set("Content-Type", "application/json");
            headers.set("HTTP-Referer", "<YOUR_SITE_URL>"); // Optional
            headers.set("X-Title", "<YOUR_SITE_NAME>"); // Optional

            String requestBody = String.format(
                    "{\"model\": \"openai/gpt-3.5-turbo-instruct\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                    userMessage
            );
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            if (responseEntity.getBody() != null) {
                JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());
                response = responseJson.path("choices").get(0).path("message").path("content").asText("No response from API.");
            } else {
                response = "No response from API.";
            }
        } catch (Exception e) {
            response = "An error occurred while processing your request: " + e.getMessage();
        }

        return response;
    }
}
