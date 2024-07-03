package com.eastflag.nnc.auth;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
  @Email(message = "유효하지 않은 이메일입니다")
  private String email;
  String password;
}
