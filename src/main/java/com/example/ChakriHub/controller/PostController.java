package com.example.ChakriHub.controller;

import com.example.ChakriHub.payload.request.PostRequestDto;
import com.example.ChakriHub.payload.response.PostResponseDto;
import com.example.ChakriHub.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Post")
public class PostController {

    PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addPost(@ModelAttribute PostRequestDto postRequestDto) {
        try {
            MultipartFile pic=postRequestDto.getCoverPhoto();
            postService.addPost(postRequestDto,pic);
            return ResponseEntity.ok("Post added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error adding post: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@RequestBody PostRequestDto postRequestDto, @PathVariable Long id) {
        try {
            postService.updatePosts(postRequestDto, id);
            return ResponseEntity.ok("Post updated successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error updating post: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePosts(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostResponseDto>>getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.findByUserId(userId));
    }
}
