package com.example.ChakriHub.service.impl;

import com.example.ChakriHub.config.cvparsing.SkillMatcherService;
import com.example.ChakriHub.entity.candidate.Candidate;
import com.example.ChakriHub.entity.post.Post;
import com.example.ChakriHub.payload.response.MatchedCandidateResponseDto;
import com.example.ChakriHub.payload.response.MatchedPostResponseDto;
import com.example.ChakriHub.repository.CandidateRepository;
import com.example.ChakriHub.repository.PostRepository;
import com.example.ChakriHub.repository.RecruterRepository;
import com.example.ChakriHub.service.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    CandidateRepository candidateRepository;
    PostRepository postRepository;
    RecruterRepository recruterRepository;
    SkillMatcherService skillMatcherService;

    public SuggestionServiceImpl
            (CandidateRepository candidateRepository,
             PostRepository postRepository,
             RecruterRepository recruterRepository,
             SkillMatcherService skillMatcherService) {

        this.candidateRepository = candidateRepository;
        this.postRepository = postRepository;
        this.recruterRepository = recruterRepository;
        this.skillMatcherService = skillMatcherService;

    }


    @Override
    public List<MatchedCandidateResponseDto> suggestionToRecruter(Long postId) {
        List<Candidate> candidates = candidateRepository.findAll();
        Post targetPost = postRepository.findById(postId).orElse(null);

        if (targetPost == null) {
            return new ArrayList<>();
        }

        Map<Long, List<String>> candidateSkillsMap = new HashMap<>();
        Map<Long, Double> matchPercentageMap = new HashMap<>();

        for (Candidate candidate : candidates) {
            List<String> extractedSkills = skillMatcherService.extractSkills(candidate.getCvInText());
            candidateSkillsMap.put(candidate.getId(), extractedSkills);
        }

        for (Candidate candidate : candidates) {
            double matchPercentage = skillMatcherService.calculateMatchPercentage(candidate.getCvInText(), targetPost.getBody());
            matchPercentageMap.put(candidate.getId(), matchPercentage);
        }

        List<Candidate> sortedCandidates = candidates.stream()
                .map(candidate -> new AbstractMap.SimpleEntry<>(candidate,
                        skillMatcherService.calculateMatchPercentage(candidate.getCvInText(), targetPost.getBody())))
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<MatchedCandidateResponseDto> candidateResponses = new ArrayList<>();
        for (Candidate candidate : sortedCandidates) {
            MatchedCandidateResponseDto responseDto = new MatchedCandidateResponseDto();
            responseDto.setCandidateId(candidate.getId());
            responseDto.setCandidateName(candidate.getFullName());
            responseDto.setMatchedSkills(candidateSkillsMap.get(candidate.getId()));
            responseDto.setMatchPercentage(matchPercentageMap.get(candidate.getId()));
            responseDto.setUsername(candidate.getUser().getUsername());
            responseDto.setCvSummery(candidate.getCvSummary());

            candidateResponses.add(responseDto);
        }

        return candidateResponses;
    }


    @Override
    public List<MatchedPostResponseDto> suggestionToCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);

        if (candidate == null) {
            return new ArrayList<>();
        }

        List<MatchedPostResponseDto> matchedPosts = new ArrayList<>();

        for (Post post : postRepository.findAll()) {
            List<String> postSkills = skillMatcherService.extractSkills(post.getBody());

            double matchPercentage = skillMatcherService.calculateMatchPercentage(candidate.getCvInText(), post.getBody());

            if (matchPercentage > 0) {
                MatchedPostResponseDto responseDto = new MatchedPostResponseDto();
                responseDto.setId(post.getId());
                responseDto.setDescription(post.getBody());
                responseDto.setRequiredSkills(postSkills);
                responseDto.setMatchPercentage(matchPercentage);


                matchedPosts.add(responseDto);
            }
        }
        matchedPosts.sort((p1, p2) -> Double.compare(p2.getMatchPercentage(), p1.getMatchPercentage()));

        return matchedPosts;
    }



    @Override
    public List<MatchedCandidateResponseDto> topCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        Map<Long, List<String>> candidateSkillsMap = new HashMap<>();

        for (Candidate candidate : candidates) {
            List<String> extractedSkills = skillMatcherService.extractSkills(candidate.getCvInText());
            candidateSkillsMap.put(candidate.getId(), extractedSkills);
        }

        List<Candidate> sortedCandidates = candidates.stream()
                .sorted((c1, c2) -> Integer.compare(
                        candidateSkillsMap.get(c2.getId()).size(),
                        candidateSkillsMap.get(c1.getId()).size()
                ))
                .limit(5)
                .collect(Collectors.toList());

        List<MatchedCandidateResponseDto> candidateResponses = new ArrayList<>();
        for (Candidate candidate : sortedCandidates) {
            MatchedCandidateResponseDto responseDto = new MatchedCandidateResponseDto();
            responseDto.setCandidateId(candidate.getId());
            responseDto.setCandidateName(candidate.getFullName());
            responseDto.setMatchedSkills(candidateSkillsMap.get(candidate.getId()));
            responseDto.setMatchPercentage((double) candidateSkillsMap.get(candidate.getId()).size());
            responseDto.setUsername(candidate.getUser().getUsername());

            candidateResponses.add(responseDto);
        }

        return candidateResponses;
    }

 ArrayList arrayList=new ArrayList();

}
