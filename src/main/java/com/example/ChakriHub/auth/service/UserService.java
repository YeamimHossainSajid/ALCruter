package com.example.ChakriHub.auth.service;

import com.example.ChakriHub.auth.dto.request.UserRequestDTO;
import com.example.ChakriHub.auth.dto.request.UserRoleRequestDTO;
import com.example.ChakriHub.auth.dto.request.UserUpdateRequestDto;
import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import com.example.ChakriHub.auth.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    public void create(UserRequestDTO requestDto, MultipartFile file) throws IOException;
    public CustomUserResponseDTO readOne(Long id );
    public User setUserRoles(UserRoleRequestDTO requestDTO );
    public void updateUser(Long id, UserUpdateRequestDto userRequestDTO, MultipartFile file) throws IOException;
    public CustomUserResponseDTO searchByUsername(String username);

}