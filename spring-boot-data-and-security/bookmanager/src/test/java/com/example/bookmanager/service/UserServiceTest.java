package com.example.bookmanager.service;

import com.example.bookmanager.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @Test
    void test(){
        userService.put();

//        System.out.println(">> "+usersRepository.findByEmail("newUser@naver.com"));
        System.out.println(usersRepository.findAll());
    }


}