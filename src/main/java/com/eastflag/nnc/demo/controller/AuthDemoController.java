package com.eastflag.nnc.demo.controller;

import com.eastflag.nnc.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthDemoController {

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
