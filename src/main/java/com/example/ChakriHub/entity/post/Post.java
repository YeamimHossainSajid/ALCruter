package com.example.ChakriHub.entity.post;


import com.example.ChakriHub.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String body;
    String skills;
    String mail;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
