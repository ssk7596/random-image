package com.hackerearth.zeta.randomimage.controller;

import com.hackerearth.zeta.randomimage.model.Image;
import com.hackerearth.zeta.randomimage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping
    public ResponseEntity<byte[]> hi(){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.getRandomImage());
    }

    @GetMapping("/default")
    public ResponseEntity<byte[]> getDefaultImage() throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.getDefaultImage());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<byte[]> getRandomImage(@PathVariable Long id){
        return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.getImageById(id));
    }

    @GetMapping("/images")
    public List<Image> getAllImages(){
        return imageService.findImages();
    }
}
