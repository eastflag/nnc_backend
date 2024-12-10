package com.eastflag.nnc.board.service;

import com.eastflag.nnc.board.dto.BoardLikeDto;
import com.eastflag.nnc.board.repository.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardLikeRepository boardLikeRepository;

    public void setLike(BoardLikeDto boardLikeDto) {
        // board id에 대해서 사용자의 likeability를 가져온다.


    }
}
