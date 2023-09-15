package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
//    @Modifying
//    @Query(value = "update book set category='none'", nativeQuery = true)
//    void update();

    // 가독성이 떨어지는 쿼리 메소드 예제
    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

}
