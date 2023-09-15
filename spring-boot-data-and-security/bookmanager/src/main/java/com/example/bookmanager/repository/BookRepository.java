package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.dto.BookNameAndCategory;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
//    @Modifying
//    @Query(value = "update book set category='none'", nativeQuery = true)
//    void update();

    // 가독성이 떨어지는 쿼리 메소드 예제
    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    // JPQL 쿼리.
    // jpa 의 entity 를 기반으로 하는 쿼리를 생성하기 위한 것
    // 여기서 Book 은 table 이름이 아니라 entity 의 이름으로 처리된 걸 볼 수 있다.
    // 또한 컬럼 이름이 아닌 필드 이름으로 쿼리를 생성하는 걸 볼 수 있다.
    // 따라서, 이러한 JPQL 쿼리는 쿼리 메소드 생성과 유사하며 네이티브 쿼리와는 차이가 존재한다.
    // 쿼리 포메팅 방법. ?+숫자 또는 네임 기반
//    @Query(value = "select b from Book b " +
//            "where name = ?1 and createdAt >= ?2 and updatedAt >= ?3 and category is null")
//    List<Book> findByNameRecentry(String name, LocalDateTime createdAt, LocalDateTime updatedAt);
    @Query(value = "select b from Book b " +
            "where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null")
    List<Book> findByNameRecentry(
            @Param("name") String name,
            @Param("createdAt")LocalDateTime createdAt,
            @Param("updatedAt")LocalDateTime updatedAt);

//    @Query(value = "select b.name as name, b.category as category from Book b")
//    List<Tuple> findBookNameAndCategory();
    @Query(value = "select new com.example.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "select new com.example.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(PageRequest pageRequest);





}
