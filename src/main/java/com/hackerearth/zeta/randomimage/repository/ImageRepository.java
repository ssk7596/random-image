package com.hackerearth.zeta.randomimage.repository;

import com.hackerearth.zeta.randomimage.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
