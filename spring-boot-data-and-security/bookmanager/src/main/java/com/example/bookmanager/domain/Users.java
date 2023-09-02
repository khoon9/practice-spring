package com.example.bookmanager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    // 데이터베이스 객체가 언제 ~했는지 jpa
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
