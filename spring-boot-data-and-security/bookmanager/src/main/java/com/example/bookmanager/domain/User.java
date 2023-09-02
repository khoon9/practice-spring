package com.example.bookmanager.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class User {
    @NonNull
    private String name;
    @NonNull
    private String email;
    // 데이터베이스 객체가 언제 ~했는지 jpa
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
