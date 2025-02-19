package com.example.ChakriHub.auth.dto.response;

import java.util.Set;

public interface CustomUserResponseDTO {

    Long getId();

    String getUsername();

    String getEmail();

    String getProfilpic();

    Set< RoleInfo > getRoles();

    interface RoleInfo {
        Long getId();

        String getRoleType();
    }
}
