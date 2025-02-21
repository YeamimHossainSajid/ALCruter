package com.example.ChakriHub.repository;

import com.example.ChakriHub.entity.post.Post;
import com.example.ChakriHub.payload.response.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("""
        select p
        FROM Post p
""")
    public List<PostResponseDto> findAllPosts();

    @Query("""
select p
from Post p
where p.user.id=:userId
""")
    public List<Post> findByUserId(Long userId);
}
