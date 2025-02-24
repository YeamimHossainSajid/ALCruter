package com.example.ChakriHub.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RecruterRequestDto {

    private String name;

    private MultipartFile coverPhoto;

    private String companyName;

    private String officeLocation;

    private String companyDiscription;

    private String industryType;

    private String bio;

    private String phoneNumber;

    private Long userId;

}
