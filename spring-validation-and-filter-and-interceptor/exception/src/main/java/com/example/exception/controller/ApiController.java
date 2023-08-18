package com.example.exception.controller;

import com.example.exception.dto.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Validated
public class ApiController {
    @GetMapping("")
    public User get(
            @Size(min = 2)
            @RequestParam String name,
            @Min(1)
            @RequestParam Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10+age;

        return user;
    }
    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    // Global 설정으로 다룬 예외처리 보다 우선하여 아래 메소드가 동작한다. 오버라이딩.
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
//        System.out.println("**************");
//        System.out.println("api controller error");
//        System.out.println("**************");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }

}
