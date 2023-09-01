package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    // http://localhost:9090/api/server/hello
    // response 를 받아올 것
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","sehun").queryParam("age", 25)
                .encode()
                .build()
                .toUri();
        log.info(uri.toString());

        // RestTemplate 로 통신
        RestTemplate restTemplate = new RestTemplate();

        // getForObject 메소드가 실행되는 순간이, 클라이언트에서 서버로 http 가 붙는 순간

        // getForObject 은 get 메소드에서 Object 을 가져오겠다는 것
        // String result = restTemplate.getForObject(uri, String.class);
        // UserResponse result = restTemplate.getForObject(uri, UserResponse.class);
        // getForEntity 은 get 메소드에서 Entity 을 가져오겠다는 것
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        log.info(result.getStatusCode().toString());
        log.info(result.getBody().toString());
        // ResponseEntity<UserResponse> 에서 UserResponse 은
        // responseBody 가 UserResponse 타입이라는 의미
        return result.getBody();
    }
    public UserResponse post(){
        // http://localhost:9090/api/server/user/{userID}/name/{userName}
        // .expand(~,...) 또는 buildAndExpand(~,...) 을 통해서 {} 에 값을 넣어줄 수 있다.
        // 유저 등록하는 요청
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userID}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        log.info(uri.toString());
        // 본래 http body 가 필요한데, 우리는 object 와 rest template 만을 사용하여 http body json 을 uri 로 보낼 수 있다.
        // http body -> object -> object mapper -> json -> rest template -> http body json

        // object 작성
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(25);
        // restTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();
        // 요청 보내기와 수신
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);
        // ResponseEntity 내용 확인
        log.info(response.getStatusCode().toString());
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());

        return response.getBody();
    }

    // Header 를 붙여서 보내는 게 현업에선 일반적이기에 실습
    public UserResponse exchange(){
        // URI 구성
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userID}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        log.info(uri.toString());
        //
        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(25);
        // RequestEntity 구성
        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorication", "abcd")
                .header("custom-header", "fffff")
                .body(userRequest);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);

        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        // URI 구성
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userID}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        log.info(uri.toString());
        // 바디 구성
        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(25);
        // generic 바디 구성 (<- Header 포함)
        Req<UserRequest> requestBody = new Req<>();
        requestBody.setHeader(
                new Req.Header()
        );
        requestBody.setRBody(
                userRequest
        );
        // RequestEntity 구성
        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorication", "abcd")
                .header("custom-header", "fffff")
                .body(requestBody);

        // generic type에 대해 .class 불가 -> new ParameterizedTypeReference<~>(){}
        // 여기선 ResponseEntity<Req<UserResponse>> 에 의해 일부 생략하여 new ParameterizedTypeReference<>(){} 만 기재
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<UserResponse>> response =  restTemplate
                .exchange(requestEntity, new ParameterizedTypeReference<>(){});
        return response.getBody();

    }

}
