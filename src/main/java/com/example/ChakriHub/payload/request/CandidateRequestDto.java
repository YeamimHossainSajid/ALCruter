package com.example.ChakriHub.payload.request;

import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidateRequestDto {

    String fullName;

    String phoneNumber;

    String location;

    String skills;

    String language;

    String portfolioLinks;

    String preferedPossion;

    String yearsOfExperience;

    MultipartFile coverPic;

    String educationalQualifications;

    String pastExperience;

    MultipartFile cv;

}
