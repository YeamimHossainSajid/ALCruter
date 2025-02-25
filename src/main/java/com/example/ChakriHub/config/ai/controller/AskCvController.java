package com.example.ChakriHub.config.ai.controller;

import com.example.ChakriHub.config.ai.service.AskCvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ai")
public class AskCvController {
    AskCvService askCvService;
    public AskCvController(AskCvService askCvService) {
        this.askCvService = askCvService;
    }
    @GetMapping("cv/question/{id}/{text}")
    public ResponseEntity<String> askCv(@PathVariable Long id,@PathVariable String text) {
        return ResponseEntity.ok(askCvService.askCv(id, text));
    }
}
