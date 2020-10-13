package com.hackerearth.zeta.randomimage.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image {

    @Id
    private Long id;

    @Lob
    private byte[] image;

    public Image(){}

    public Image(Long id, byte[] randomImage) {
        this.id = id;
        this.image = randomImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
