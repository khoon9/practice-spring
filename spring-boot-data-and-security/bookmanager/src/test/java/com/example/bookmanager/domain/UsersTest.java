package com.example.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UsersTest {
    @Test
    void test(){
        Users users = new Users(null, "example@naver.com", "sehun", LocalDateTime.now(), LocalDateTime.now());

        System.out.println(">>> " + users);
    }

}

