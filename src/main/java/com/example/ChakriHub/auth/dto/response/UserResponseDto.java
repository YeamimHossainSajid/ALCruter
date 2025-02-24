package com.example.ChakriHub.auth.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface UserResponseDto {

    Long getId();

    String getUsername();

    String getEmail();

    String getProfilpic();

    String getChoose();

    CandidateInfo getCandidate();

    interface CandidateInfo {
        Long getId();
        String getFullName();
        String getBio();
        String getPhoneNumber();
        String getLocation();
        String getSkills();
        String getLanguage();
        String getAbout();
        String getPortfolioLinks();
        String getPreferedPossion();
        String getYearsOfExperience();
        String getCoverPic();
        String getEducationalQualifications();
        String getPastExperience();
        String getCv();
    }

    RecruterInfo getRecruter();

    public interface RecruterInfo {
        Long getId();
        String getName();
        String getCoverPhoto();
        String getCompanyName();
        String getOfficeLocation();
        String getCompanyDiscription();
        String getIndustryType();
        String getPhoneNumber();
        String getBio();
    }

    List<PostResponseDto> getPosts(); // Assuming each user can have multiple posts

    interface PostResponseDto {
        Long getId();
        String getBody();
        String getPicture();
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM yyyy, h:mm a", timezone = "Asia/Dhaka")
        LocalDateTime getCreatedDate();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM yyyy, h:mm a", timezone = "Asia/Dhaka")
        LocalDateTime getUpdatedDate();

    }

}

