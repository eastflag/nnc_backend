package com.eastflag.nnc.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDemoController {

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }

    @PostMapping("/demo")
    @PreAuthorize("hasAuthority('admin:create')")
    public String post() {
        return "POST:: admin controller";
    }

    @PutMapping("/demo")
    @PreAuthorize("hasAuthority('admin:update')")
    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping("/demo")
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete() {
        return "DELETE:: admin controller";
    }
}
