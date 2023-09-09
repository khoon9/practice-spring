package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("bookname");
        book.setAuthorId(1L);
        book.setPublisher_id(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

}