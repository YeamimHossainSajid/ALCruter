package com.example.ChakriHub.auth.service;

import com.example.ChakriHub.auth.dto.request.LoginRequestDTO;
import com.example.ChakriHub.auth.dto.response.LoginResponseDTO;

public interface IAuthenticationService {
    LoginResponseDTO login(LoginRequestDTO requestDTO );
}
