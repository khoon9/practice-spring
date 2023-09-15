package com.example.bookmanager.domain;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void test(){
        User user = new User();
        user.setName("sehun");
        user.setEmail("sehun@naver.com");

        System.out.println(">>> " + user);
    }

}

