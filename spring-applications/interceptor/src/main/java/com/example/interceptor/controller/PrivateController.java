package com.example.interceptor.controller;

import com.example.interceptor.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 내부사용자, 세션이 인증된 사용자에게만 넘길 것 -> 권한 차이 How?
@Auth
@Slf4j
@RestController
@RequestMapping("/api/private")
public class PrivateController {
    @GetMapping("/hello")
    public String hello(){
        log.info("private hello controller");
        return "private hello";
    }
}
