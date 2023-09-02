package com.example.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test(){
        User user = new User("example@naver.com", "sehun", LocalDateTime.now(), LocalDateTime.now());
        User.builder().name("sehun").email("example@naver.com").build();
        System.out.println(">>> " + user);
    }

}