package com.example.springioc;

import org.springframework.stereotype.Component;

import java.util.Base64;
// 스프링에게 객체로 관리해달라고 요청. 이 클래스의 관리를 스프링에게 넘기기 위한 어노테이션이다.
// Component 에 이름을 안 주면 base64Encoder 와 같이, Bean 의 이름이 앞글자가 소문자인 형태로 생성
// 이름을 주면 해당 이름대로 Bean 의 이름이 정해진다.
@Component("base74Encoder")
public class Base64Encoder implements IEncoder{
    public String encode(String message){

        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
