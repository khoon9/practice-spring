package com.example.restaurant.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 데이터 Entity 변경사항이 생겼을 때, 프론트엔드와 마주한 서비스가 변경할 우려가 생긴다
// 서비스와 직접적으로 밎닿은 dto 을 따로 만든다.
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto {
    private int index;
    private String title;                   // 음식명, 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
    private String readAddress;             // 도로명
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 음식, 가게 이미지 주소
    private boolean isVisit;                // 방문 여부
    private int visitCount;                 // 방문 카운트
    private LocalDateTime lastVisitDate;    // 마지막 방문 일자

}
