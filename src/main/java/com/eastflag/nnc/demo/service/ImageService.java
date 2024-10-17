package com.eastflag.nnc.demo.service;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.DataUri;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.demo.dto.ImageDTO;
import com.eastflag.nnc.demo.entity.ImageEntity;
import com.eastflag.nnc.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Integer saveImage(ImageDTO imageDTO) {
        ImageEntity imageEntity;

        if (StringUtils.hasText(imageDTO.getDataUri())) {
            DataUri dataUri = new DataUri(imageDTO.getDataUri());

            imageEntity = ImageEntity.builder()
                    .type(dataUri.getContentType())
                    .image(dataUri.getData())
                    .build();
        } else {
            imageEntity = ImageEntity.builder()
                    .type(imageDTO.getType())
                    .image(imageDTO.getImage())
                    .build();
        }

        imageRepository.save(imageEntity);

        return imageEntity.getId();
    }

    public ImageDTO getImage(int id) {
        var imageEntity = imageRepository.findById(id);
        if (imageEntity.isPresent()) {
            return ImageDTO.builder()
                    .image(imageEntity.get().getImage())
                    .type(imageEntity.get().getType())
                    .build();
        }
        return null;
    }
}
