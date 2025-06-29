package com.example.ChakriHub.entity.candidate;

import com.example.ChakriHub.auth.model.User;
import com.example.ChakriHub.entity.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 2000000, message = "Full name must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String fullName;

    @Size(max = 2000000, message = "Bio must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String bio;

    @Size(max = 2000000, message = "Phone number must be up to 2,000,000 characters") // Adjusted for consistency
    @Column(length = 2000000)
    private String phoneNumber;

    @Size(max = 2000000, message = "Location must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String location;

    @Size(max = 2000000, message = "Skills must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String skills;

    @Size(max = 2000000, message = "Language must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String language;

    @Size(max = 2000000, message = "About must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String about;

    @Size(max = 2000000, message = "Portfolio links must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String portfolioLinks;

    @Size(max = 2000000, message = "Preferred position must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String preferedPossion;

    @Size(max = 2000000, message = "Years of experience must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String yearsOfExperience;

    @Size(max = 2000000, message = "Cover picture URL must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String coverPic;

    @Size(max = 2000000, message = "Educational qualifications must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String educationalQualifications;

    @Size(max = 2000000, message = "Past experience must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String pastExperience;

    @Size(max = 2000000, message = "CV file name must be up to 2,000,000 characters")
    @Column(length = 2000000)
    private String cv;

    @Size(max = 2000000, message = "CV in text must be up to 2,000,000 characters") // Updated to 2,000,000
    @Column(length = 2000000)
    private String cvInText;

    @Size(max = 20000, message = "CV in text must be up to 2,000,0 characters") // Updated to 2,000,000
    @Column(length = 20000)
    private String cvSummary;

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

    @ManyToMany(mappedBy = "candidates")
    private Set<Post> posts = new HashSet<>();
}
