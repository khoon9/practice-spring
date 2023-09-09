package com.example.bookmanager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity{
    @Id
    @GeneratedValue
    private Long Id;
    private Long bookId;
    private float averageReviewScore;
    private int reviewCount;

    // 프리미티브, 렙퍼드 차이. null 허용 여부에 결정적
    // float 와 Float, int 와 Int
    // 렙퍼드 타입은 null check 을 하지 않으면 nullpoint 예외가 발생

}
