package com.eastflag.nnc.admin;

import com.eastflag.nnc.board.dto.BoardDto;
import com.eastflag.nnc.board.dto.BoardSearchDto;
import com.eastflag.nnc.board.repository.BoardCustomRepository;
import com.eastflag.nnc.board_category.dto.BoardCategoryDto;
import com.eastflag.nnc.board_category.model.BoardCategory;
import com.eastflag.nnc.board_category.repository.BoardCategoryRepository;
import com.eastflag.nnc.user.dto.UserDto;
import com.eastflag.nnc.user.dto.UserSearchDto;
import com.eastflag.nnc.user.model.User;
import com.eastflag.nnc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final BoardCategoryRepository boardCategoryRepository;

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

    public List<BoardCategoryDto> getBoardCategoryList() {
        List<BoardCategory> boardCategoryList = boardCategoryRepository.findAll();
        return boardCategoryList.stream().map(category -> {
            return BoardCategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .useYn(category.getUseYn())
                    .build();
        }).collect(Collectors.toList());
    }
}
