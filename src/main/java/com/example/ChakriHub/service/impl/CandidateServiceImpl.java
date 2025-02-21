package com.example.ChakriHub.service.impl;

import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import com.example.ChakriHub.auth.model.User;
import com.example.ChakriHub.auth.repository.UserRepo;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    CandidateRepository candidateRepository;
    UserRepo userRepo;
    @Autowired
    CloudneryImageServiceImpl cloudneryImageService;

    public CandidateServiceImpl(CandidateRepository candidateRepository, UserRepo userRepo) {
        this.candidateRepository = candidateRepository;
        this.userRepo = userRepo;
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
        candidate.setBio(candidateRequestDto.getBio());
        candidate.setAbout(candidateRequestDto.getAbout());
        candidate.setUser(userRepo.findById(candidateRequestDto.getUserId()).get());

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
        candidate.setBio(candidateRequestDto.getBio());
        candidate.setAbout(candidateRequestDto.getAbout());

//        candidate.setCustomUserResponseDTO(new CustomUserResponseDTO() {
//            @Override
//            public Long getId() {
//                return candidateRequestDto.getUser().getId();  // Getting from candidateRequestDto
//            }
//
//            @Override
//            public String getUsername() {
//                return candidateRequestDto.getUser().getUsername();
//            }
//
//            @Override
//            public String getEmail() {
//                return candidateRequestDto.getUser().getEmail();
//            }
//
//            @Override
//            public String getProfilpic() {
//                return candidateRequestDto.getUser().getProfilpic();
//            }
//
//        });

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
      Candidate candidate1=  candidateRepository.save(candidate);
        User user =userRepo.findById(candidateRequestDto.getUserId()).get();
        user.setChoose("candidate");
        userRepo.save(user);
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
