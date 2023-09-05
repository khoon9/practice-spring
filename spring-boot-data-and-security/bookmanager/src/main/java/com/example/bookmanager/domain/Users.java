package com.example.bookmanager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;
    private String name;
    private String email;
    // 데이터베이스 객체가 언제 ~했는지 jpa
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // collector 정의
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
}
