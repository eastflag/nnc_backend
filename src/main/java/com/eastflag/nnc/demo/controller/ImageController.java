package com.eastflag.nnc.demo.controller;

import com.ctc.wstx.util.DataUtil;
import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.demo.dto.ImageDTO;
import com.eastflag.nnc.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/api/v1/demo/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;


    @PostMapping("/upload1")
    public ResponseEntity<CommonResponse> imageUpload1(@RequestParam("type") String type,
                                                      @RequestParam("image") MultipartFile image) throws IOException {
        var imageDTO = ImageDTO.builder()
                .type(type)
                .image(image.getBytes())
                .build();

        var id = imageService.saveImage(imageDTO);

        var responseDTO = ImageDTO.builder()
                .url("http://localhost:9090/api/v1/demo/image/" + id)
                .build();

        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .data(responseDTO)
                .build();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload2")
    public ResponseEntity<CommonResponse> imageUpload2(@RequestBody ImageDTO imageDTO) throws IOException {
        var id = imageService.saveImage(imageDTO);

        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .data("http://localhost:9090/api/v1/demo/image/" + id)
                .build();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getImage(@PathVariable int id) {
        var imageDTO = imageService.getImage(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", imageDTO.getType());

        return ResponseEntity.ok()
                .headers(headers)
                .body(imageDTO.getImage());
    }
}
