package com.example.ChakriHub.entity.post;


import com.example.ChakriHub.auth.model.User;
import com.example.ChakriHub.entity.candidate.Candidate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Size(max = 2000000, message = "body must be up to 2,000,000 characters")
    @Column(length = 2000000)
    String body;


    private String picture;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM yyyy, h:mm a,timezone = \"Asia/Dhaka")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM yyyy, h:mm a,timezone = \"Asia/Dhaka")
    private LocalDateTime updatedDate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "post_candidate",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id")
    )
    private Set<Candidate> candidates = new HashSet<>();


}
