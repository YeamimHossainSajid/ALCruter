package com.example.ChakriHub.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostRequestDto {

    private String body;

    private MultipartFile coverPhoto;

    private Long userId;

}
