package com.example.MessageApplication.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    public String uploadImage(MultipartFile file);

}
