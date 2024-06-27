package com.eastflag.nnc.demo.repository;

import com.eastflag.nnc.demo.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
}
