package com.example.bookmanager.domain;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // 테이블에는 book_id 가 존재하겠지만,
    // Jpa 에서는 entity 로 set get 을 하게 되면 자동으로 relation 을 맺을 수 있도록 해준다.

    @OneToOne(optional = false)
    private Book book;

//    private Long bookId;
    private float averageReviewScore;
    private int reviewCount;

    // 프리미티브, 렙퍼드 차이. null 허용 여부에 결정적
    // float 와 Float, int 와 Int
    // 렙퍼드 타입은 null check 을 하지 않으면 nullpoint 예외가 발생

}
