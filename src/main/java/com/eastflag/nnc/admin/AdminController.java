package com.eastflag.nnc.admin;

import com.eastflag.nnc.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/user/list")
    public List<UserDto> getUserList(UserDto searchCondition, Pageable pageable) {
        log.info("user: {}, pageable: {}", searchCondition, pageable);
        return adminService.getUserList();
    }
}
