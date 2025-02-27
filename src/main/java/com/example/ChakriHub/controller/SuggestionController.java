package com.example.ChakriHub.controller;

import com.example.ChakriHub.payload.response.MatchedCandidateResponseDto;
import com.example.ChakriHub.payload.response.MatchedPostResponseDto;
import com.example.ChakriHub.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recruiter")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;


    @GetMapping("/suggestions/{id}")
    public ResponseEntity<List<MatchedCandidateResponseDto>> suggestionToRecruter(@PathVariable Long id) {
        List<MatchedCandidateResponseDto> suggestions = suggestionService.suggestionToRecruter(id);
        return new ResponseEntity<>(suggestions, HttpStatus.OK);
    }


    @GetMapping("/candidates/{candidateId}/suggestions")
    public ResponseEntity<List<MatchedPostResponseDto>> suggestionToCandidate(@PathVariable Long candidateId) {
        List<MatchedPostResponseDto> suggestions = suggestionService.suggestionToCandidate(candidateId);
        return new ResponseEntity<>(suggestions, HttpStatus.OK);
    }

    @GetMapping("/top-candidates")
    public List<MatchedCandidateResponseDto> getTopCandidates() {
        return suggestionService.topCandidates();
    }


}
