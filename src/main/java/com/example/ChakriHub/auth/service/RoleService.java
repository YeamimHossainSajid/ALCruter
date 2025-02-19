package com.example.ChakriHub.auth.service;

import com.example.ChakriHub.auth.dto.response.CustomRoleResponseDTO;

public interface RoleService {

    public CustomRoleResponseDTO readOne(Long id );
    public String delete( Long id );

}
