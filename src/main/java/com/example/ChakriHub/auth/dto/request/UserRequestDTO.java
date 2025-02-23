package com.example.ChakriHub.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UserRequestDTO {
        private String username;
        private String email;
        private String password;
        private MultipartFile profilpic; // Include MultipartFile

        // Getters and Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public MultipartFile getProfilpic() { return profilpic; }
        public void setProfilpic(MultipartFile profilpic) { this.profilpic = profilpic; }
}
