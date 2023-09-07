package com.example.bookmanager.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = MyEntityListener.class)
public class Book implements Auditable{
    // 지금은 h2 을 사용중에 있기 때문에 default 인 auto 에 의해 hibernate sequence 값을 사용할 것
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    @PrePersist
//    void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//    @PreUpdate
//    void preUpdate(){
//        this.updatedAt = LocalDateTime.now();
//    }

}
