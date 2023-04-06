package com.ceres.filing.controller;

import com.ceres.filing.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController@RequestMapping("/image")
public class ImageController {
    @Autowired
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file);
        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }
    @GetMapping("/{image}")
    ResponseEntity<?> retrieve(@PathVariable String image) throws IOException{
        byte[] imageData = imageService.downloadImage(image);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(image);
    }
}
