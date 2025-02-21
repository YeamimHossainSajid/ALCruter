package com.example.ChakriHub.entity.candidate;

import com.example.ChakriHub.auth.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fullName;

    String bio;

    String phoneNumber;

    String location;

    String skills;

    String language;

    String about;

    String portfolioLinks;

    String preferedPossion;

    String yearsOfExperience;

    String coverPic;

    String educationalQualifications;

    String pastExperience;

    String cv;

    @Size(max = 2000000, message = "CV in text must be up to 20,000 characters")
    String cvInText;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null && user.getCandidate() != this) {
            user.setCandidate(this);
        }
    }
}
