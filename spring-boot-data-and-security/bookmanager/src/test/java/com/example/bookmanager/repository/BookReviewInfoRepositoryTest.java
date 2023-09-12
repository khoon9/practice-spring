package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.BookReviewInfo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();

        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> "+bookReviewInfoRepository.findAll());

    }

    @Test
    void crudTest02(){
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository
                .findAll().get(0)
                .getBook();

        System.out.println(">>> "+result);

        BookReviewInfo result2 = bookRepository
                .findAll().get(0)
                .getBookReviewInfo();

        System.out.println(">>> "+result2);
    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("Jpa 초격차 패키지 0"+(int) bookRepository.count());
        book.setAuthorId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> "+bookReviewInfoRepository.findAll());
    }
}