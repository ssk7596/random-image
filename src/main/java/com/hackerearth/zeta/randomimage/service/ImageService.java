package com.hackerearth.zeta.randomimage.service;

import com.hackerearth.zeta.randomimage.model.Image;
import com.hackerearth.zeta.randomimage.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    private static String DEFAULT_IMAGE_PATH = "lara.jpg";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ImageRepository imageRepository;

    public byte[] getDefaultImage() throws IOException {
        ClassPathResource defaultImage = new ClassPathResource(DEFAULT_IMAGE_PATH);
        byte[] imageByte = StreamUtils.copyToByteArray(defaultImage.getInputStream());
        return imageByte;
    }

    public byte[] getRandomImage(){
        return restTemplate.getForObject("https://picsum.photos/500",byte[].class);
    }

    @Transactional
    public byte[] getImageById(Long id){
        Optional<Image> image = imageRepository.findById(id);
        if(!image.isEmpty()){
            LOGGER.info("Id present in database, returning saved image");
            return image.get().getImage();
        }
        LOGGER.info("Id not present in database, returning random image");
        byte[] randomImage =  getRandomImage();
        imageRepository.save(new Image(id,randomImage));
        return randomImage;
    }

    public List<Image> findImages(){
        return imageRepository.findAll();
    }
}
