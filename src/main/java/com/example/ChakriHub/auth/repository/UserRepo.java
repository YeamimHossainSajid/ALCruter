package com.example.ChakriHub.auth.repository;

import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import com.example.ChakriHub.auth.dto.response.UserResponseDto;
import com.example.ChakriHub.auth.model.Status;
import com.example.ChakriHub.auth.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long > {

    List<User> findAllByStatus(Status status);

    @EntityGraph(attributePaths = "posts")
    User findByUsername(String username);

    @EntityGraph( attributePaths = { "roles" } )
    User findByUsernameOrEmail(String username, String email );

    @EntityGraph(attributePaths = "posts")
    @Query("""
            SELECT u FROM User u where u.username=:username
            """)
    UserResponseDto searchByUsername(String username );

    boolean existsByEmail( String email );

    @EntityGraph( attributePaths = { "roles" } )
    @Query( """
                SELECT
                    user
                FROM
                    User user
                WHERE
                    user.id = :id
            """ )
    CustomUserResponseDTO findUserByUserId(@Param( "id" ) Long id );


}
