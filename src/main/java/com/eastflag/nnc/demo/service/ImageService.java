package com.eastflag.nnc.demo.service;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.demo.dto.ImageDTO;
import com.eastflag.nnc.demo.entity.ImageEntity;
import com.eastflag.nnc.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public CommonResponse saveImage(ImageDTO imageDTO) {
        var imageEntity = ImageEntity.builder()
                .type(imageDTO.getType())
                .image(imageDTO.getImage())
                .build();

        imageRepository.save(imageEntity);

        var responseImageDTO = ImageDTO.builder()
                .url("http://localhost:9090/api/v1/demo/image/" + imageEntity.getId().toString())
                .build();

        return CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .data(responseImageDTO)
                .build();
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
