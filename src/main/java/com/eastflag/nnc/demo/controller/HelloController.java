package com.eastflag.nnc.demo.controller;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo/hello")
@Log4j2
public class HelloController {
    // 모든 methood 허용
    @RequestMapping("/hello1")
    public String hello() {
        log.debug("all methods are permitted");
        return "Hello all method permitted test";
    }

    // GET만 허용
    @GetMapping("/hello2")
    public String hello2() {
        return "Hello GET test";
    }

    // query parameter 예
    @GetMapping("/hello3")
    public String hello3(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    // 동적 url 파라메터 예.
    @GetMapping("/hello4/{name}/{num}")
    public String hello4(@PathVariable String name, @PathVariable Integer num) {
        return "Hello " + name + " " + num;
    }

    // query parameter 로 페이징하는 예. ?page=2&size=10&sort=created,DESC
    @GetMapping("/hello5")
    public String hello5(Pageable page) {
        log.debug("pageable: {} {} {}",  page.getPageNumber(), page.getPageSize(), page.getSort());
        return "Hello " + page;
    }

    // post method도 query parameter 사용 가능
    @PostMapping("/hello6")
    public String hello6(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    // response body를  json으로 보내기
    @PostMapping("/hello7")
    public CommonResponse<?> hello7() {
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return result;
    }

    // response body + response header 제어
    @PostMapping("/hello8")
    public ResponseEntity<CommonResponse<?>> hello8() {
        var result = CommonResponse.builder()
                .code(0)
                .message(ResponseMessage.SUCCESS)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // request body를 json으로 매핑
    @PostMapping("/hello9")
    public CommonResponse hello9(@RequestBody CommonResponse result) {
        return result;
    }
}
