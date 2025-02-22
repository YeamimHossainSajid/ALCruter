package com.example.ChakriHub.payload.request;

import jakarta.validation.constraints.Size;
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

    @Size(max = 255000, message = "Full name must be up to 255 characters")
    String fullName;

    @Size(max = 255000, message = "Phone number must be up to 255 characters")
    String phoneNumber;

    @Size(max = 255000, message = "Location must be up to 255 characters")
    String location;

    @Size(max = 255000, message = "Bio must be up to 255 characters")
    String bio;

    @Size(max = 255000, message = "About must be up to 255 characters")
    String about;

    @Size(max = 255000, message = "Skills must be up to 255 characters")
    String skills;

    @Size(max = 255000, message = "Language must be up to 255 characters")
    String language;

    @Size(max = 255000, message = "Portfolio links must be up to 255 characters")
    String portfolioLinks;

    @Size(max = 255000, message = "Preferred position must be up to 255 characters")
    String preferedPossion;

    @Size(max = 255000, message = "Years of experience must be up to 255 characters")
    String yearsOfExperience;

    MultipartFile coverPic;

    @Size(max = 255000, message = "Educational qualifications must be up to 255 characters")
    String educationalQualifications;

    @Size(max = 255000, message = "Past experience must be up to 255 characters")
    String pastExperience;

    MultipartFile cv;

    Long userId;
}

