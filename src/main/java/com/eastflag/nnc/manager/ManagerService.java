package com.eastflag.nnc.manager;

import com.eastflag.nnc.board.dto.BoardDto;
import com.eastflag.nnc.board.dto.BoardSearchDto;
import com.eastflag.nnc.board.model.Board;
import com.eastflag.nnc.board.repository.BoardCustomRepository;
import com.eastflag.nnc.board.repository.BoardRepository;
import com.eastflag.nnc.board_category.repository.BoardCategoryRepository;
import com.eastflag.nnc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final BoardCustomRepository boardCustomRepository;
    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final UserRepository userRepository;

    public Page<BoardDto> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable) {
        return boardCustomRepository.findBoardByCategory(boardSearchDto, pageable);
    }

    public void createBoard(BoardDto boardDto) {
        var boardCategory = boardCategoryRepository.findById(boardDto.getCategoryId());
        var user = userRepository.findById(boardDto.getUserId());
        var board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .user(user.orElseThrow(() -> new IllegalArgumentException("user id doesn't exist")))
                .boardCategory(boardCategory.orElseThrow(() -> new IllegalArgumentException("user id doesn't exist")))
                .build();
        boardRepository.save(board);
    }
}
