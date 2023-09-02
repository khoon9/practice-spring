package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UsersRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() { // create, read, update, delete
        userRepository.save(new Users(null, "example@naver.com", "sehun", LocalDateTime.now(), LocalDateTime.now()));

        System.out.println(">>> "+userRepository.findAll());
    }

}