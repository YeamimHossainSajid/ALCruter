package com.example.ChakriHub.service;

import com.example.ChakriHub.entity.candidate.Candidate;
import com.example.ChakriHub.payload.request.CandidateRequestDto;
import com.example.ChakriHub.payload.response.CandidateResponseDto;

import java.io.IOException;
import java.util.List;

public interface CandidateService {

    public CandidateResponseDto getCandidate(Long id);
    public List<CandidateResponseDto> getAllCandidates();
    public void addCandidate(CandidateRequestDto candidateRequestDto) throws IOException;
    public void updateCandidate(CandidateRequestDto candidateRequestDto,Long id) throws IOException;
    public void deleteCandidate(Long id);

}
