package com.example.bookmanager.domain;

import com.example.bookmanager.domain.converter.BookStatusConverter;
import com.example.bookmanager.repository.dto.BookStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "deleted = false")
//@DynamicUpdate
public class Book extends BaseEntity {
    // 지금은 h2 을 사용중에 있기 때문에 default 인 auto 에 의해 hibernate sequence 값을 사용할 것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long authorId;

    private String category;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Publisher publisher;

//    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<MiddleOfBookAndAuthor> middleOfBookAndAuthors = new ArrayList<>();

    private boolean deleted;
    // 판매 상태
    // @Convert 와 @Converter 차이 유의
    @Convert(converter = BookStatusConverter.class)
    private BookStatus status;

    public void addMiddleOfBookAndAuthor(MiddleOfBookAndAuthor... middleOfBookAndAuthors){
        Collections.addAll(this.middleOfBookAndAuthors, middleOfBookAndAuthors);
    }
}
