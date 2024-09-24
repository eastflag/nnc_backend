package com.eastflag.nnc.admin;

import com.eastflag.nnc.user.dto.UserDto;
import com.eastflag.nnc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public List<UserDto> getUserList() {
        List<UserDto> userList = userRepository.findAllBySearch();
        return userList;
    }
}
