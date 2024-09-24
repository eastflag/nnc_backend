package com.eastflag.nnc.user.repository;

import com.eastflag.nnc.user.dto.UserDto;

import java.util.List;

public interface UserCustomRepository {
    List<UserDto> findAllBySearch();
}
