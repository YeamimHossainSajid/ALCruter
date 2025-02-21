package com.example.ChakriHub.controller;

import com.example.ChakriHub.payload.request.RecruterRequestDto;
import com.example.ChakriHub.payload.response.RecruterResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ChakriHub.service.RecruterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruterController {

    private final RecruterService recruterService;

    public RecruterController(RecruterService recruterService) {
        this.recruterService = recruterService;
    }


    @GetMapping
    public ResponseEntity<List<RecruterResponseDto>> getAllRecruters() {
        List<RecruterResponseDto> recruiters = recruterService.getAllRecruters();
        return ResponseEntity.ok(recruiters);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecruterResponseDto> getRecruterById(@PathVariable Long id) {
        RecruterResponseDto recruiter = recruterService.getRecruterById(id);
        return ResponseEntity.ok(recruiter);
    }


    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createRecruter(@ModelAttribute RecruterRequestDto recruterRequestDto) throws IOException {
        recruterService.createRecruter(recruterRequestDto);
        return ResponseEntity.ok("Recruiter created successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecruter(@RequestBody RecruterRequestDto recruterRequestDto, @PathVariable Long id) throws IOException {
        recruterService.updateRecruter(recruterRequestDto, id);
        return ResponseEntity.ok("Recruiter updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruter(@PathVariable Long id) {
        recruterService.deleteRecruterById(id);
        return ResponseEntity.ok("Recruiter deleted successfully");
    }
}

