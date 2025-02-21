package com.example.ChakriHub.entity.recruter;

import com.example.ChakriHub.auth.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recruter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private String coverPhoto;

    private String companyName;

    private String officeLocation;

    private String companyDiscription;

    private String industryType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User users;

    public void setUsers(User users) {
        this.users = users;
        if (users != null) {
            users.setRecruter(this);
        }
    }

}
