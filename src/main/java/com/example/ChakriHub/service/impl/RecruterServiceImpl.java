package com.example.ChakriHub.service.impl;

import com.example.ChakriHub.entity.recruter.Recruter;
import com.example.ChakriHub.payload.request.RecruterRequestDto;
import com.example.ChakriHub.payload.response.RecruterResponseDto;
import com.example.ChakriHub.repository.RecruterRepository;
import com.example.ChakriHub.service.RecruterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruterServiceImpl implements RecruterService {

    public RecruterRepository recruterRepository;

    public RecruterServiceImpl(RecruterRepository recruterRepository) {
        this.recruterRepository = recruterRepository;
    }


    public Recruter ConvartToRecruter(RecruterRequestDto recruterRequestDto,Recruter recruter) {

        recruter.setName(recruterRequestDto.getName());
        recruter.setCompanyDiscription(recruterRequestDto.getCompanyDiscription());
        recruter.setCompanyName(recruterRequestDto.getCompanyName());
        recruter.setCoverPhoto(recruterRequestDto.getCoverPhoto());
        recruter.setIndustryType(recruterRequestDto.getIndustryType());
        recruter.setOfficeLocation(recruterRequestDto.getOfficeLocation());

        return recruter;
    }

    public RecruterResponseDto ConvertToRecruterResponse(Recruter recruter) {

        RecruterResponseDto recruterResponseDto = new RecruterResponseDto();

        recruterResponseDto.setId(recruter.getId());
        recruterResponseDto.setName(recruter.getName());
        recruterResponseDto.setCompanyDiscription(recruter.getCompanyDiscription());
        recruterResponseDto.setCoverPhoto(recruter.getCoverPhoto());
        recruterResponseDto.setCompanyName(recruter.getCompanyName());
        recruterResponseDto.setIndustryType(recruter.getIndustryType());
        recruterResponseDto.setOfficeLocation(recruter.getOfficeLocation());

        return recruterResponseDto;
    }


    @Override
    public List<RecruterResponseDto> getAllRecruters() {
        List<Recruter> recruters = recruterRepository.findAll();
        List<RecruterResponseDto> recruterResponseDtos =
                recruters.stream()
                        .map(this::ConvertToRecruterResponse)
                        .collect(Collectors.toList());

        return recruterResponseDtos;
    }

    @Override
    public RecruterResponseDto getRecruterById(Long id) {
        Recruter recruter = recruterRepository.findById(id).orElse(null);
        return ConvertToRecruterResponse(recruter);
    }

    @Override
    public void createRecruter(RecruterRequestDto recruterRequestDto) {
        Recruter recruter = ConvartToRecruter(recruterRequestDto,new Recruter());
        recruterRepository.save(recruter);
    }

    @Override
    public void updateRecruter(RecruterRequestDto recruterRequestDto, Long id) {
        Recruter recruter = recruterRepository.findById(id).orElse(null);
        Recruter updatedRecruter = ConvartToRecruter(recruterRequestDto,recruter);
         recruterRepository.save(updatedRecruter);

    }

    @Override
    public void deleteRecruterById(Long id) {
      recruterRepository.deleteById(id);
    }
}
