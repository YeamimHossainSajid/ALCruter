package com.example.ChakriHub.payload.response;

import lombok.Data;

@Data
public class RecruterResponseDto {

    private Long id;

    private String name;

    private String coverPhoto;

    private String companyName;

    private String officeLocation;

    private String companyDiscription;

    private String industryType;

    private String bio;

    private String phoneNumber;
}
