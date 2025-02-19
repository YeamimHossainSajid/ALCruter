package com.example.ChakriHub.service;

import com.example.ChakriHub.entity.candidate.Candidate;
import com.example.ChakriHub.payload.request.CandidateRequestDto;
import com.example.ChakriHub.payload.response.CandidateResponseDto;

import java.util.List;

public interface CandidateService {

    public Candidate getCandidate(Long id);
    public List<CandidateResponseDto> getAllCandidates();
    public void addCandidate(CandidateRequestDto candidateRequestDto);
    public void updateCandidate(CandidateRequestDto candidateRequestDto,Long id);
    public void deleteCandidate(Long id);

}
