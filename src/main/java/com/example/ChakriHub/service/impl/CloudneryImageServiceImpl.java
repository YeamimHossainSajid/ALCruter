package com.example.ChakriHub.service.impl;

import com.cloudinary.Cloudinary;
import com.example.ChakriHub.service.CloudneryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudneryImageServiceImpl implements CloudneryImageService {

    @Autowired
    public Cloudinary cloudinary;

    @Override
    public Map upload(MultipartFile file) throws IOException {
      Map data=  this.cloudinary.uploader().upload(file.getBytes(),Map.of());
        return data;
    }
}
