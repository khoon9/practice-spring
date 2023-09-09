package com.example.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UsersTest {
    @Test
    void test(){
        Users users = new Users();
        users.setName("sehun");
        users.setEmail("sehun@naver.com");

        System.out.println(">>> " + users);
    }

}

