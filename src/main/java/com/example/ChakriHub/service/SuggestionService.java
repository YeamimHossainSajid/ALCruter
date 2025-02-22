package com.example.ChakriHub.service;

import com.example.ChakriHub.entity.candidate.Candidate;
import com.example.ChakriHub.payload.response.CandidateResponseDto;
import com.example.ChakriHub.payload.response.MatchedCandidateResponseDto;
import com.example.ChakriHub.payload.response.MatchedPostResponseDto;
import com.example.ChakriHub.payload.response.PostResponseDto;

import java.util.List;

public interface SuggestionService {

    public List<MatchedCandidateResponseDto> suggestionToRecruter(Long id);
    public List<MatchedPostResponseDto> suggestionToCandidate(Long candidateId);

}
