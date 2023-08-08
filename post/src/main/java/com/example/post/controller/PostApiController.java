package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @PostMapping("/post01")
    public void postApi01(@RequestBody Map<String,Object> requestData) {
        requestData.entrySet().forEach(stringObjectEntry -> {
            System.out.println("key: " + stringObjectEntry.getKey());
            System.out.println("value: " + stringObjectEntry.getValue());
        });
    }
    // 인텔리제이 방식의 코딩. 01 방법보다 더 간결하다. 하지만 마찬가지로 파라미터가 설정되어있지 않다.
    @PostMapping("/post02")
    public void postApi02(@RequestBody Map<String,Object> requestData){
        requestData.forEach((key, value) -> {
            System.out.println("key: " + key);
            System.out.println("value: " + value);
        });
    }

    // dto
    @PostMapping("/post03")
    public void postApi02(@RequestBody PostRequestDto requestData){
        System.out.println(requestData);
    }
}
