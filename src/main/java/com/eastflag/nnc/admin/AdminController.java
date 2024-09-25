package com.eastflag.nnc.admin;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.user.dto.UserSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/user/paged_list")
    public ResponseEntity<CommonResponse<?>> getUserList(UserSearchDto userSearchDto, Pageable pageable) {
        log.debug("user: {}, pageable: {}", userSearchDto, pageable);
        var pageUserDto =  adminService.getUserList(userSearchDto, pageable);
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .data(pageUserDto)
                .build();
        return ResponseEntity.ok(result);
    }
}
