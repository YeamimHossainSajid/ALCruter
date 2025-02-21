package com.example.ChakriHub.service;

import com.example.ChakriHub.entity.recruter.Recruter;
import com.example.ChakriHub.payload.request.RecruterRequestDto;
import com.example.ChakriHub.payload.response.RecruterResponseDto;

import java.io.IOException;
import java.util.List;

public interface RecruterService {

    public List<RecruterResponseDto> getAllRecruters();
    public RecruterResponseDto getRecruterById(Long id);
    public void createRecruter(RecruterRequestDto recruterRequestDto) throws IOException;
    public void updateRecruter(RecruterRequestDto recruterRequestDto, Long id) throws IOException;
    public void deleteRecruterById(Long id);

}
