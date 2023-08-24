package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {
    // https://openapi.naver.com/v1/search/local.json
    // ?query=%EC%A3%BC%EC%8B%9D
    // &display=10
    // &start=1
    // &sort=random
    @GetMapping("naver")
    public String naver(){
        String query = "갈비집";
        String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","갈비집")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        log.info("uri : {}", uri);
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","qHFTqtDFPsVTbn1PBArG")
                .header("X-Naver-Client-Secret","q0yy_1CzEC")
                .build();

        ResponseEntity<String> result  = restTemplate.exchange(req,String.class);

        return result.getBody();
    }

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
