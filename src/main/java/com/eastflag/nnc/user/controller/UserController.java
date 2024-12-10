package com.eastflag.nnc.user.controller;

import com.eastflag.nnc.auth.CustomUserDetails;
import com.eastflag.nnc.board.dto.BoardDto;
import com.eastflag.nnc.board.dto.BoardLikeDto;
import com.eastflag.nnc.board.service.BoardService;
import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.user.ChangePasswordRequest;
import com.eastflag.nnc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService service;
    private final BoardService boardService;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/board/{id}/like")
    public ResponseEntity<CommonResponse<?>> createBoard(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                         @RequestBody BoardLikeDto boardLikeDto) {
        boardLikeDto.setUserId(customUserDetails.getId());
        log.debug("boardLikeDto: {}", boardLikeDto);

        boardService.setLike(boardLikeDto);
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return ResponseEntity.ok(result);
    }
}
