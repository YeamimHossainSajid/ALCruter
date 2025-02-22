package com.example.ChakriHub.service.impl;

import com.example.ChakriHub.config.cvparsing.SkillMatcherService;
import com.example.ChakriHub.entity.candidate.Candidate;
import com.example.ChakriHub.entity.post.Post;
import com.example.ChakriHub.payload.response.CandidateResponseDto;
import com.example.ChakriHub.payload.response.MatchedCandidateResponseDto;
import com.example.ChakriHub.payload.response.MatchedPostResponseDto;
import com.example.ChakriHub.payload.response.PostResponseDto;
import com.example.ChakriHub.repository.CandidateRepository;
import com.example.ChakriHub.repository.PostRepository;
import com.example.ChakriHub.repository.RecruterRepository;
import com.example.ChakriHub.service.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        List<String> postSkills = skillMatcherService.extractSkills(targetPost.getBody());

        for (Candidate candidate : candidates) {
            double matchPercentage = skillMatcherService.calculateMatchPercentage(candidate.getCvInText(), targetPost.getBody());
            matchPercentageMap.put(candidate.getId(), matchPercentage);
        }

        List<MatchedCandidateResponseDto> candidateResponses = new ArrayList<>();
        for (Candidate candidate : candidates) {
            MatchedCandidateResponseDto responseDto = new MatchedCandidateResponseDto();
            responseDto.setCandidateId(candidate.getId());
            responseDto.setCandidateName(candidate.getFullName());
            responseDto.setMatchedSkills(candidateSkillsMap.get(candidate.getId()));
            responseDto.setMatchPercentage(matchPercentageMap.get(candidate.getId()));

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

        List<String> candidateSkills = skillMatcherService.extractSkills(candidate.getCvInText());

        List<Post> allPosts = postRepository.findAll();
        List<MatchedPostResponseDto> matchedPosts = new ArrayList<>();

        for (Post post : allPosts) {
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



        return matchedPosts;
    }

}
