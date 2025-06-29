package com.example.ChakriHub.payload.response;

import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidateResponseDto {

    private Long id;

    String fullName;

    String phoneNumber;

    String location;

    String skills;

    String language;

    String portfolioLinks;

    String preferedPossion;

    String yearsOfExperience;

    String coverPic;

    String educationalQualifications;

    String pastExperience;

    String cv;

    CustomUserResponseDTO customUserResponseDTO;

    String about;

    String bio;

    LocalDateTime createdDate;

    String summarizedCV;
}
