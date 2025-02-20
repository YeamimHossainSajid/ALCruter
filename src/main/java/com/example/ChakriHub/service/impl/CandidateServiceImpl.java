package com.example.ChakriHub.service.impl;

import com.example.ChakriHub.entity.candidate.Candidate;
import com.example.ChakriHub.entity.recruter.Recruter;
import com.example.ChakriHub.payload.request.CandidateRequestDto;
import com.example.ChakriHub.payload.response.CandidateResponseDto;
import com.example.ChakriHub.payload.response.RecruterResponseDto;
import com.example.ChakriHub.repository.CandidateRepository;
import com.example.ChakriHub.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    CandidateRepository candidateRepository;
    @Autowired
    CloudneryImageServiceImpl cloudneryImageService;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate convertToEntity(CandidateRequestDto candidateRequestDto, Candidate candidate, MultipartFile coverImage,MultipartFile cv) throws IOException {

        Map<String, Object> heroUploadResult = cloudneryImageService.upload(coverImage);
        String coverImageUrl = (String) heroUploadResult.get("secure_url");

        Map<String, Object> heroUploadResult1 = cloudneryImageService.upload(coverImage);
        String cv1 = (String) heroUploadResult1.get("secure_url");

        candidate.setCv(cv1);
        candidate.setCoverPic(coverImageUrl);
        candidate.setLanguage(candidateRequestDto.getLanguage());
        candidate.setCreatedDate(LocalDateTime.now());
        candidate.setEducationalQualifications(candidateRequestDto.getEducationalQualifications());
        candidate.setLocation( candidateRequestDto.getLocation());
        candidate.setSkills(candidateRequestDto.getSkills());
        candidate.setFullName(candidateRequestDto.getFullName());
        candidate.setPhoneNumber(candidateRequestDto.getPhoneNumber());
        candidate.setYearsOfExperience(candidateRequestDto.getYearsOfExperience());
        candidate.setPreferedPossion(candidateRequestDto.getPreferedPossion());
        candidate.setPortfolioLinks(candidateRequestDto.getPortfolioLinks());
        candidate.setPastExperience(candidateRequestDto.getPastExperience());

        return candidate;

    }

    public CandidateResponseDto convertToDto(Candidate candidateRequestDto) {

        CandidateResponseDto candidate = new CandidateResponseDto();

        candidate.setCv(candidateRequestDto.getCv());
        candidate.setCoverPic(candidateRequestDto.getCoverPic());
        candidate.setLanguage(candidateRequestDto.getLanguage());
        candidate.setCreatedDate(LocalDateTime.now());
        candidate.setEducationalQualifications(candidateRequestDto.getEducationalQualifications());
        candidate.setLocation( candidateRequestDto.getLocation());
        candidate.setSkills(candidateRequestDto.getSkills());
        candidate.setFullName(candidateRequestDto.getFullName());
        candidate.setPhoneNumber(candidateRequestDto.getPhoneNumber());
        candidate.setYearsOfExperience(candidateRequestDto.getYearsOfExperience());
        candidate.setPreferedPossion(candidateRequestDto.getPreferedPossion());
        candidate.setPortfolioLinks(candidateRequestDto.getPortfolioLinks());
        candidate.setPastExperience(candidateRequestDto.getPastExperience());

        return candidate;


    }



    @Override
    public CandidateResponseDto getCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        CandidateResponseDto candidateResponseDto = convertToDto(candidate);
        return candidateResponseDto;
    }

    @Override
    public List<CandidateResponseDto> getAllCandidates() {
        List<Candidate> candidate = candidateRepository.findAll();
        List<CandidateResponseDto> candidateResponseDtos =
                candidate.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList());
        return candidateResponseDtos;

    }

    @Override
    public void addCandidate(CandidateRequestDto candidateRequestDto) throws IOException {
        Candidate candidate=convertToEntity(candidateRequestDto,new Candidate(),candidateRequestDto.getCoverPic(),candidateRequestDto.getCv());
        candidateRepository.save(candidate);
    }

    @Override
    public void updateCandidate(CandidateRequestDto candidateRequestDto, Long id) throws IOException {
   Candidate candidate=candidateRepository.findById(id).orElse(null);
     Candidate updatedCandidate=convertToEntity(candidateRequestDto,candidate,candidateRequestDto.getCoverPic(),candidateRequestDto.getCv());
     candidateRepository.save(updatedCandidate);
    }

    @Override
    public void deleteCandidate(Long id) {
   candidateRepository.deleteById(id);
    }
}
