package com.eastflag.nnc.auth.controller;

import com.eastflag.nnc.auth.dto.AuthenticationRequest;
import com.eastflag.nnc.auth.dto.RegisterRequest;
import com.eastflag.nnc.auth.service.AuthenticationService;
import com.eastflag.nnc.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/signup")
  public ResponseEntity<CommonResponse> register(@RequestBody @Valid RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/logout")
  public ResponseEntity<CommonResponse> logout(@RequestHeader("Authorization") String accessToken) {
    return ResponseEntity.ok(service.logout(accessToken));
  }
}
