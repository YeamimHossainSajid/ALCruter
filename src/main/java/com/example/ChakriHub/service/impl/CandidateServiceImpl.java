package com.example.ChakriHub.service.impl;

import com.example.ChakriHub.entity.candidate.Candidate;
import com.example.ChakriHub.payload.request.CandidateRequestDto;
import com.example.ChakriHub.payload.response.CandidateResponseDto;
import com.example.ChakriHub.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Override
    public Candidate getCandidate(Long id) {
        return null;
    }

    @Override
    public List<CandidateResponseDto> getAllCandidates() {
        return List.of();
    }

    @Override
    public void addCandidate(CandidateRequestDto candidateRequestDto) {

    }

    @Override
    public void updateCandidate(CandidateRequestDto candidateRequestDto, Long id) {

    }

    @Override
    public void deleteCandidate(Long id) {

    }
}
