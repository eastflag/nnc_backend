package com.eastflag.nnc.manager;

import com.eastflag.nnc.board.dto.BoardDto;
import com.eastflag.nnc.board.dto.BoardSearchDto;
import com.eastflag.nnc.board.repository.BoardCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final BoardCustomRepository boardCustomRepository;

    public Page<BoardDto> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable) {
        return boardCustomRepository.findBoardByCategory(boardSearchDto, pageable);
    }
}
