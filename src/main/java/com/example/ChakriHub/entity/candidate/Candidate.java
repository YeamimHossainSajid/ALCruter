package com.example.ChakriHub.entity.candidate;

import com.example.ChakriHub.auth.model.User;
import jakarta.persistence.*;
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

    String phoneNumber;

    String location;

    String skills;

    String language;

    String portfolioLinks;

    String preferedPossion;

    String yearsOfExperience;

    String coverPic;

    String educationalQualifications;

    String pastExperience;

    String cv;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
