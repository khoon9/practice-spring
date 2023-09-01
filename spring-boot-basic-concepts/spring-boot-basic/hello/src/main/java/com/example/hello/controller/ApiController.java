package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러임을 명시하는 어노테이션
@RequestMapping("/api") // URI 맵핑
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }
}


