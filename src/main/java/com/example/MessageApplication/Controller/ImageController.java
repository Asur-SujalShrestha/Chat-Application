package com.example.MessageApplication.Controller;

import com.example.MessageApplication.Service.ImageUploadService;
import com.example.MessageApplication.Service.Impl.ImageUploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/sendImage")
public class ImageController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/uploadImage")
    @CrossOrigin("http://localhost:5173")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageUploadService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Image upload failed: " + e.getMessage());
        }
    }
}
