package com.eastflag.nnc.demo;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.demo.dto.TvDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ValidationController {

    @PostMapping("/demo/tv")
    public ResponseEntity<CommonResponse> authDemo(@RequestBody @Valid TvDTO tvDTO) {
        log.info("tv {}", tvDTO.toString());
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return ResponseEntity.ok(result);
    }
}
