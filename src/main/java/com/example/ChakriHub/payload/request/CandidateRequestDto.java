package com.example.ChakriHub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    String coverPic;

    String educationalQualifications;

    String pastExperience;

    String cv;

    Long userId;
}
