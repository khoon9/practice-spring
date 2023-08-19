package com.example.filter.dto;

import lombok.*;

// @NoArgsConstructor : 기본 생성자를 뜻한다.
// @Getter 와 @Setter 는 자동으로 getter 와 setter 를 만들어 주는 역할
// @Data : 자동으로 getter 와 setter, 그리고 toString 까지 만들어 주는 역할
// @AllArgsConstructor : 전체 필드에 대한 매개변수를 받는 생성자를 만들어 준다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;
}
