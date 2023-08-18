package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.File;
import java.lang.reflect.Field;

//@RestControllerAdvice(basePackages = "com.example.controller")
@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {

    // REST API 이기 때문에 ResponseEntity 을 반환할 것이다.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        // 지정하는 예외를 잡고자 할 때
        System.out.println(e.getClass());
        // 서버에서 잡는 에러 : INTERNAL_SERVER_ERROR
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 하나 이상의 에러가 BindingResult 에 묶여서 온다.
    @ExceptionHandler(value = MethodArgumentNotValidException. class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            String value = fieldError.getRejectedValue().toString();
            System.out.println("--------------");
            System.out.println(fieldName);
            System.out.println(message);
            System.out.println(value);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    // 파라미터가 Validation 을 통과하지 못한 경우에 발생하는 오류.
    // 하나 이상의 에러가 ConstraintViolations 에 묶여서
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e){
        System.out.println(e.getClass());
        e.getConstraintViolations().forEach(error ->{
            System.out.println(error);
            // String value = (String) error.getPropertyPath().;
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    // 파라미터 또는 그 value가 누락되었고 API 메소드의 required 가 true(기본 설정)인 상태일 때 발생
    // 단일 에러가 온다.
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e){
        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidValue = e.getMessage();
        System.out.println(fieldName);
        System.out.println(fieldType);
        System.out.println(invalidValue);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
