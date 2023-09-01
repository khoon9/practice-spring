package com.example.restaurant.wishlist.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    // 본래는,
    // WishListService 가 NaverClient 에 대해 의존성 주입을 받으므로
    // Mocking 처리하여 NaverClient 의 return 을 지정해야 하지만 이번엔 생략
    @Test
    public void searchTest(){
        var result = wishListService.search("갈비집");

        System.out.println(result);

        Assertions.assertNotNull(result);
    }

}
