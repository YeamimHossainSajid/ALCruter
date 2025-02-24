package com.example.ChakriHub.auth.dto.response;


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
    }

    List<PostResponseDto> getPosts(); // Assuming each user can have multiple posts

    interface PostResponseDto {
        Long getId();
        String getTitle();
        String getBody();
        String getSkills();
        String getMail();
    }

}

