package com.example.bookmanager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users", indexes = {@Index(columnList = "name")}, uniqueConstraints ={@UniqueConstraint(columnNames = {"email"})})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    private String name;
    private String email;
    // 데이터베이스 객체가 언제 ~했는지 jpa
    //@Column(name = "crtdat")
    //@Column(unique = true)
    //@Column
    @Column(updatable = false)
    private LocalDateTime createdAt;
    //@Column(insertable = false)
    private LocalDateTime updatedAt;

    // DB 에서
    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    public Users(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    // collector 정의
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
}
