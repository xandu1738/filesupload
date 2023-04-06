package com.ceres.filing.service;

import com.ceres.filing.entity.ImageEntity;
import com.ceres.filing.repository.ImageRepository;
import com.ceres.filing.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public  String uploadImage(MultipartFile multipartFile) throws IOException {
        ImageEntity imageEntity = imageRepository.save(ImageEntity.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .imageData(ImageUtils.compressImage(multipartFile.getBytes())).build()
        );
        if (imageEntity!=null){
            return "success";
        }
        return null;
    }
    public byte[] downloadImage(String fileName){
        Optional<ImageEntity> dbImage = imageRepository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImage.get().getImageData());
        return images;
    }
}
