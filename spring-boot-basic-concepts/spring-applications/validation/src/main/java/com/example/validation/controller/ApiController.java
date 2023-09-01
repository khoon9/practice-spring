package com.example.validation.controller;

import com.example.validation.dto.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user")
    public Object user(@Valid @RequestBody User user, BindingResult bindingResult){
        // 예외 처리 대용. BindingResult 은 @Valid 에 대한 결과를 받는 객체
        if (bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : "+field.getField());
                System.out.println(message);

                sb.append("\nfield :"+field.getField());
                sb.append("\nmessage :"+message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        System.out.println(user);

        return user;
    }

}
