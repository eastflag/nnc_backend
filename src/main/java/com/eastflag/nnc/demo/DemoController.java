package com.eastflag.nnc.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class DemoController {

  @GetMapping("/auth/demo")
  public ResponseEntity<String> authDemo() {
    log.info("path: {}", "/api/v1/auth/demo");
    return ResponseEntity.ok("Hello from Method...");
  }


  @GetMapping("/user/demo")
  public ResponseEntity<String> userDemo() {
    log.info("path: {}", "/api/v1/user/demo");
    return ResponseEntity.ok("Hello from /api/vi/user/demo");
  }
}