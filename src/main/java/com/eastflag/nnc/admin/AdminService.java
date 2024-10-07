package com.eastflag.nnc.admin;

import com.eastflag.nnc.user.dto.UserDto;
import com.eastflag.nnc.user.dto.UserSearchDto;
import com.eastflag.nnc.user.model.User;
import com.eastflag.nnc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public Page<UserDto> getUserList(UserSearchDto userSearchDto, Pageable pageable) {
        return userRepository.findAllBySearch(userSearchDto, pageable);
    }

    public void updateUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            user.setRole(userDto.getRole());
            user.setNickname(userDto.getNickname());
            userRepository.save(user);
        }
    }

    public void deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
