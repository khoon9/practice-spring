package com.example.bookmanager.service;

import com.example.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void test(){
        userService.put();

//        System.out.println(">> "+usersRepository.findByEmail("newUser@naver.com"));
        System.out.println(userRepository.findAll());
    }


}