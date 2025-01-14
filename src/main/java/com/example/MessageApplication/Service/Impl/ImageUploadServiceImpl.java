package com.example.MessageApplication.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.MessageApplication.Service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file){
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString(); // Returns the uploaded image URL
        }
        catch (Exception e){
            throw new RuntimeException("Image upload failed");
        }
    }
}
