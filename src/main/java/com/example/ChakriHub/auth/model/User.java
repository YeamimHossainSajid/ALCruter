package com.example.ChakriHub.auth.model;

import com.example.ChakriHub.entity.candidate.Candidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "_user" )
//@SuperBuilder
//@SQLRestriction( "is_active = TRUE" )
//@SQLDelete( sql = "UPDATE _user SET is_active = FALSE WHERE id = ?" )
//@NamedEntityGraph(
//        name = "User.roles",
//        attributeNodes = @NamedAttributeNode("roles")
//)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false ,unique = true)
    private String username;

    @Column( nullable = false )
    private String email;

    @Column( nullable = false )
    @NotEmpty
    private String password;

    private String profilpic;

    private Status status;


    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH },
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "_user_roles",
            joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "roles_id" )
    )

    private Set<Role> roles = new LinkedHashSet<>();


    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", unique = true)
    private Candidate candidate;

//    public void setCandidate(Candidate candidate) {
//        this.candidate = candidate;
//        if (candidate != null ) {
//            candidate.setUser(this);
//        }
//    }
    @Column()
    private Long candiId;

    // Getter and setter for candidateId
    public Long getCandidateId() {
        return candiId;
    }

    public void setCandidateId(Long candidateId) {
        this.candiId = candidateId;
    }

   private String choose=null;


}
