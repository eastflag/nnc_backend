package com.eastflag.nnc.user.repository;

import com.eastflag.nnc.user.dto.UserDto;
import com.eastflag.nnc.user.dto.UserSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {
    Page<UserDto> findAllBySearch(UserSearchDto userSearchDto, Pageable pageable);
}
