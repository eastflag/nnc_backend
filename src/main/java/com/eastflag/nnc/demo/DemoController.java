package com.eastflag.nnc.demo;

import com.eastflag.nnc.auth.CustomUserDetails;
import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.demo.dto.ImageDTO;
import com.eastflag.nnc.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {
  private final ImageService imageService;

  @GetMapping("/auth/demo")
  public ResponseEntity<String> authDemo() {
    log.info("path: {}", "/api/v1/auth/demo");
    return ResponseEntity.ok("Hello from Method...");
  }


  @GetMapping("/user/demo")
  public ResponseEntity<String> userDemo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
    log.info("user: {}", customUserDetails.getUsername());
    return ResponseEntity.ok("Hello from /api/vi/user/demo");
  }


}
