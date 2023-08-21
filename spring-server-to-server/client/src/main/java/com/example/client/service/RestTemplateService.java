package com.example.client.service;

import com.example.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
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
        // encode 는 파라미터에 대해 인코딩을 해야 안정적이기에 붙이는 것이다.
        // path 뒤에다가 queryParam("name","steve")
        // queryParam("age", 10) 와 같이 작성하여 보낼 수 있다.
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","sehun").queryParam("age", 25)
                .encode()
                .build()
                .toUri();
        // RestTemplate 로 통신
        log.info(uri.toString());

        // RestTemplate 은 다양한 쓰임이 있다
        // getForEntity 는 responseEntity 로 반환
        // getForObject 는 원하는 타입으로 반환 한다는 차이가 있다.
        RestTemplate restTemplate = new RestTemplate();
        // getForObject 메소드가 실행되는 순간이, 클라이언트에서 서버로 http 가 붙는 순간
        // getForObject 에서 가져오겠다는 get 이 아니라, http 의 get 메소드를 의미한다.

        // String result = restTemplate.getForObject(uri, String.class);
        // UserResponse result = restTemplate.getForObject(uri, UserResponse.class);
        // ResponseEntity 을 사용하였을 때, 다양한 정보를 추출할 수 있다.
        // result.getBody() 을 사용하면 responseBody
        // result.getStatusCode() 을 사용하면 http status 을 알 수 있다.
        // ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        log.info(result.getStatusCode().toString());
        log.info(result.getBody().toString());
        // ResponseEntity<UserResponse> 에서 UserResponse 은
        // responseBody 가 UserResponse 타입이라는 의미
        return result.getBody();
    }
}
