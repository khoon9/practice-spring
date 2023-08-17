package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackages = "com.example.controller")
@RestControllerAdvice(basePackages = "com.example.controller")
public class GlobalControllerAdvice {

    // 내가 잡고자 하는 메소드
    // REST API 이기 때문에 ResponseEntity 을 반환할 것이다.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        // 지정하는 예외를 잡고자 할 때
        System.out.println(e.getClass());
        System.out.println("-------------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("-------------------");
        // 서버에서 잡는 에러 : INTERNAL_SERVER_ERROR
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("--------------");
        System.out.println("global 예외 제어");
        System.out.println("--------------");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
