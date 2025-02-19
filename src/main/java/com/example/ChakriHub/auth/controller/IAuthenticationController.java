package com.example.ChakriHub.auth.controller;

import com.example.ChakriHub.auth.dto.request.LoginRequestDTO;
import com.example.ChakriHub.auth.dto.response.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {

    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO requestDTO );

}
