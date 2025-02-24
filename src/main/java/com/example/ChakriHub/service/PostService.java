package com.example.ChakriHub.service;

import com.example.ChakriHub.payload.request.CandidateRequestDto;
import com.example.ChakriHub.payload.request.PostRequestDto;
import com.example.ChakriHub.payload.response.CandidateResponseDto;
import com.example.ChakriHub.payload.response.PostResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    public PostResponseDto getPost(Long id);
    public List<PostResponseDto> getAllPosts();
    public void addPost(PostRequestDto postRequestDto, MultipartFile pic) throws IOException;
    public void updatePosts(PostRequestDto postRequestDto,Long id) throws IOException;
    public void deletePosts(Long id);
    public List<PostResponseDto> findByUserId(Long id);
}
