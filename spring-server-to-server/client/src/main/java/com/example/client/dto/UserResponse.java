package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String name;
    private int age;
}
// 1. 어떤 서버가 어떤 데이터를 준다고 정해지면
// 2. json 표준 규격을 보고 클래스를 작성
// 3. RestTemplate 으로 get 이면 getFor~ post 이면 postFor~ 사용