package com.example.ChakriHub.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Component;

@Component
public class MultipartFileConverter implements Converter<String, MultipartFile> {
    @Override
    public MultipartFile convert(String source) {
        return null; // ✅ If no file is provided, set it to null
    }
}
