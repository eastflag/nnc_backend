package com.eastflag.nnc.manager;

import com.eastflag.nnc.board.dto.BoardSearchDto;
import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager")
@PreAuthorize("hasRole('MANAGER')")
@RequiredArgsConstructor
@Log4j2
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/board/paged_list")
    public ResponseEntity<CommonResponse<?>> getUserList(BoardSearchDto boardSearchDto, Pageable pageable) {
        log.debug("user: {}, pageable: {}", boardSearchDto, pageable);
        var pagedBoardDto =  managerService.getBoardList(boardSearchDto, pageable);
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .data(pagedBoardDto)
                .build();
        return ResponseEntity.ok(result);
    }
}
