package com.example.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 아무나 사용할 수 있는 오픈 API 를 부여할 것
@RestController
@RequestMapping("/api/public")
public class PublicController {
    @GetMapping("/hello")
    public String hello(){
        return "public hello";
    }
}
