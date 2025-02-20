package com.example.ChakriHub.controller;

import com.example.ChakriHub.payload.request.CandidateRequestDto;
import com.example.ChakriHub.payload.response.CandidateResponseDto;
import com.example.ChakriHub.service.CandidateService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Get all candidates
    @GetMapping
    public ResponseEntity<List<CandidateResponseDto>> getAllCandidates() {
        List<CandidateResponseDto> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    // Get candidate by ID
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> getCandidate(@PathVariable Long id) {
        CandidateResponseDto candidate = candidateService.getCandidate(id);
        return ResponseEntity.ok(candidate);
    }

    // Create candidate
    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addCandidate(@ModelAttribute CandidateRequestDto candidateRequestDto) throws IOException {
        candidateService.addCandidate(candidateRequestDto);
        return ResponseEntity.ok("Candidate added successfully");
    }

    // Update candidate
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCandidate(@RequestBody CandidateRequestDto candidateRequestDto, @PathVariable Long id) throws IOException {
        candidateService.updateCandidate(candidateRequestDto, id);
        return ResponseEntity.ok("Candidate updated successfully");
    }

    // Delete candidate
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok("Candidate deleted successfully");
    }
}

