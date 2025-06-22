package com.example.ChakriHub.config.ai.controller;

import com.example.ChakriHub.config.ai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai")
public class OpenAiController {

    @Autowired
    private OpenAiService openAiService;

    @PostMapping("/chat")
    public String chat(@RequestParam String prompt) {
        return openAiService.getChatResponse(prompt);
    }
}

