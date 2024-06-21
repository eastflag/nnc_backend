package com.eastflag.nnc.auth;

import com.eastflag.nnc.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String nickname;
  private String email;
  private String password;
  private Role role = Role.USER;
}
