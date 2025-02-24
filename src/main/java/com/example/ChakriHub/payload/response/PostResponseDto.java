package com.example.ChakriHub.payload.response;


import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import lombok.Data;

@Data
public class PostResponseDto {
    Long id;

    String body;

    String photo;

    CustomUserResponseDTO user;
}
