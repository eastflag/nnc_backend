package com.eastflag.nnc.manager;

import com.eastflag.nnc.auth.CustomUserDetails;
import com.eastflag.nnc.board.dto.BoardDto;
import com.eastflag.nnc.board.dto.BoardSearchDto;
import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
@RequiredArgsConstructor
@Log4j2
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/board/paged_list")
    public ResponseEntity<CommonResponse<?>> getUserList(BoardSearchDto boardSearchDto, Pageable pageable) {
        log.debug("board: {}, pageable: {}", boardSearchDto, pageable);
        var pagedBoardDto =  managerService.getBoardList(boardSearchDto, pageable);
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .data(pagedBoardDto)
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<CommonResponse<?>> getBoard(@PathVariable Long id) {
        var boardDto = managerService.getBoard(id);
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .data(boardDto)
                .build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/board")
    public ResponseEntity<CommonResponse<?>> createBoard(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                         @RequestBody BoardDto boardDto) {
        boardDto.setUserId(customUserDetails.getId());
        log.debug("boardDto: {}", boardDto);

        managerService.createBoard(boardDto);
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/board")
    public ResponseEntity<CommonResponse<?>> updateBoard(@RequestBody BoardDto boardDto) {
        log.debug("boardDto: {}", boardDto);

        managerService.updateBoard(boardDto);
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return ResponseEntity.ok(result);
    }
}
