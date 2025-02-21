package com.example.ChakriHub.payload.request;

import lombok.Data;

@Data
public class PostRequestDto {

    String title;
    String body;
    String skills;
    String mail;
    Long userId;

}
