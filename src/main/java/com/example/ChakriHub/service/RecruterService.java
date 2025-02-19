package com.example.ChakriHub.service;

import com.example.ChakriHub.entity.recruter.Recruter;
import com.example.ChakriHub.payload.request.RecruterRequestDto;
import com.example.ChakriHub.payload.response.RecruterResponseDto;

import java.util.List;

public interface RecruterService {

    public List<RecruterResponseDto> getAllRecruters();
    public RecruterResponseDto getRecruterById(Long id);
    public void createRecruter(RecruterRequestDto recruterRequestDto);
    public void updateRecruter(RecruterRequestDto recruterRequestDto, Long id);
    public void deleteRecruterById(Long id);

}
