package com.example.ChakriHub.auth.dto.response;

import java.util.Set;

public interface CustomUserResponseDTO {

    Long getId();

    String getUsername();

    String getEmail();

    String getProfilpic();

    String getAddress();

    String getPhone();

    String getBio();

    Long getSold();

    Long getPropertyAdded();

    String getProfession();

    String getFullname();

    Set< RoleInfo > getRoles();

    interface RoleInfo {
        Long getId();

        String getRoleType();
    }
}
