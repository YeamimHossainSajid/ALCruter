package com.example.ChakriHub.payload.request;


import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import lombok.Data;

@Data
public class RecruterRequestDto {

    private String name;

    private String coverPhoto;

    private String companyName;

    private String officeLocation;

    private String companyDiscription;

    private String industryType;

}
