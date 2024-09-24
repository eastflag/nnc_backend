package com.eastflag.nnc.user.dto;

import com.eastflag.nnc.user.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String nickname;
    private String email;
    private String password;
}
