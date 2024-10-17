package com.eastflag.nnc.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
// @PreAuthorize("hasRole('MANAGER')") => SecurityConfiguration 에 이미 정의되어있음.
public class ManagementDemoController {

    @GetMapping
    public String get() {
        return "GET:: manager role is required";
    }

    @PostMapping
    public String post() {
        return "POST:: management controller";
    }

    @PutMapping
    public String put() {
        return "PUT:: management controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}
