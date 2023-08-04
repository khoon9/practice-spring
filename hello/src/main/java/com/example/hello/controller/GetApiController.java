package com.example.hello.controller;
// 실습을 위해서 임의로 생성한 클래스이다. 실제로는 Get API 만을 따로 모아두거나 하기 위해 별도의 클래스를 만들지는 않는다.


import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Spring Boot";
    }

    @RequestMapping(path = "hi", method = RequestMethod.GET)
    public String hi(){
        return "hi";
    }

    @GetMapping("/path-variable/basic/{name}")
    public String pathVariable(@PathVariable String name){
        System.out.println("PathVariable : "+name);
        return name;
    }

    @GetMapping("/path-variable/another-way/{id}")
    public String pathVariableAnotherWay(@PathVariable(name = "id") String pathName){
        System.out.println("PathVariable : "+pathName);
        return pathName;
    }

    // 쿼리 파라미터

    // 주소에 쿼리 내용 넣는 방법(api 요청할 때)
    // 1. path Value 를 갖기 위해서 : ? 으로 시작
    // 2. 키와 값 : key=value
    // 3. 이후 키와 값 추가 : &key=value

    // 쿼리 내용을 받는 방법(api 수신할 때)
    @GetMapping(path = "query-param01")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        // 문자열 버퍼를 생성하기 위해 만든 객체
        StringBuilder sb = new StringBuilder();

        // Mapping 은 무슨 key 가 들어올지 모름
        // 읽을 key 를 지정하는 것은 가능하나 매우 번거로움
        // 예시 : queryParam.get("name"); : name 이라는 key 의 value 를 읽으라는 명령어

        // 람다식 활용. entrySet()을 통해 Map 자료형 객체를 분할한 후 forEach 를 하여 각 entry 를 다룬다.
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            // 단순히 수신 받은 param 의 전체 내용을 한 줄로 기록하는 모습
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        // 기록한 문자열 버퍼 반환
        return sb.toString();
    }

    // 위의 Mapping 은 받아오는 param 의 key 를 지정하기에 번거롭다.
    // 따라서, 명시적으로 변수로 받기 위해서는 Map 객체가 아닌 변수 이름으로 받아온다.
    @GetMapping(path = "query-param02")
    public String queryParam2(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }

    // 400~ 대는 클라이언트가 잘못한 것. 잘못된 값을 value 로 넣었을 경우가 대표적

    // dto 형식 : 요청을 받을 때 정형화된 param 조건을 불러옴
    // @RequestParam 대신 객체가 위치할 경우,
    // 해당 객체(dto)의 변수 이름과 param 의 매칭을 하게된다.

    @GetMapping(path = "query-param03")
    public String queryParam3(UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }

}
