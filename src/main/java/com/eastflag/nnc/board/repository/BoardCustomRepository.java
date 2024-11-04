package com.eastflag.nnc.board.repository;

import com.eastflag.nnc.board.dto.BoardDto;
import com.eastflag.nnc.board.dto.BoardSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {
    Page<BoardDto> findBoardByCategory(BoardSearchDto boardSearchDto, Pageable pageable);
}
