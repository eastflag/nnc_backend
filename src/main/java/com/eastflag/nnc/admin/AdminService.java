package com.eastflag.nnc.admin;

import com.eastflag.nnc.user.dto.UserDto;
import com.eastflag.nnc.user.dto.UserSearchDto;
import com.eastflag.nnc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public Page<UserDto> getUserList(UserSearchDto userSearchDto, Pageable pageable) {
        return userRepository.findAllBySearch(userSearchDto, pageable);
    }
}
