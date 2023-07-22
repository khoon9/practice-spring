package com.example.hello.controller;
// 실습을 위해서 임의로 생성한 클래스이다. 실제로는 Get API 만을 따로 모아두거나 하기 위해 별도의 클래스를 만들지는 않는다.


import org.springframework.web.bind.annotation.*;

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
}
