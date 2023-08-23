package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
                    //HttpEntity 을 받으면, @RequestBody 을 사용한 것과 동일하다.RequestEntity 자체를 받는 것
                    //HttpEntity<String> entity,
                    @RequestBody Req<User> user,
                    @PathVariable int userId,
                    @PathVariable String userName,
                    @RequestHeader("x-authorication") String authorication,
                    @RequestHeader("custom-header") String customHeader){
        //log.info("req : {}", entity.toString());
        log.info("userId : {}, userName : {}",userId,userName);
        log.info("authorication : {}, custom : {}", authorication, customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setRBody(user.getRBody());

        return response;
    }


}
