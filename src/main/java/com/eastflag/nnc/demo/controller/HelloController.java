package com.eastflag.nnc.demo.controller;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo")
@Log4j2
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        log.debug("all methods are permitted");
        return "Hello test";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello GET test";
    }

    @GetMapping("/hello3")
    public String hello3(@RequestParam("name") String name) {
        return "Hello" + name;
    }

    @GetMapping("/hello4/{name}")
    public String hello4(@PathVariable String name) {
        return "Hello" + name;
    }

    @PostMapping("/hello5")
    public String hello5(@RequestParam("name") String name) {
        return "Hello" + name;
    }

    @PostMapping("/hello6")
    public String hello6(@RequestParam String name) {
        return "Hello " + name;
    }

    @PostMapping("/hello7")
    public CommonResponse<?> hello7(@RequestParam("name") String name) {
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return result;
    }

    @PostMapping("/hello8")
    public ResponseEntity<CommonResponse<?>> hello8(@RequestParam("name") String name) {
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/hello9")
    public CommonResponse hello8(@RequestBody CommonResponse result) {
        return result;
    }
}
