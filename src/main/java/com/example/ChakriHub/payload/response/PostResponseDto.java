package com.example.ChakriHub.payload.response;


import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {
    Long id;

    String body;

    String photo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM yyyy, h:mm a", timezone = "Asia/Dhaka")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM yyyy, h:mm a", timezone = "Asia/Dhaka")
    private LocalDateTime updatedDate;


    CustomUserResponseDTO user;
}
