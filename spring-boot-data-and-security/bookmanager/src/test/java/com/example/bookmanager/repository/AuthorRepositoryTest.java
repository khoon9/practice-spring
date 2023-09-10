package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Publisher;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void manyToManyTest(){
        Book book01 = givenBook("책01");
        Book book02 = givenBook("책02");
        Book book03 = givenBook("개발책01");
        Book book04 = givenBook("개발책02");

        Author author01 = givenAuthor("sehun");
        Author author02 = givenAuthor("steve");

        book01.addAuthor(author01);
        book02.addAuthor(author02);
        book03.addAuthor(author01, author02);
        book04.addAuthor(author01, author02);

        author01.addBook(book01,book03,book04);
        author02.addBook(book02,book03,book04);

        bookRepository.saveAll(Lists.newArrayList(book01,book02,book03,book04));
        authorRepository.saveAll(Lists.newArrayList(author01,author02));

        System.out.println("authors through book : "+bookRepository.findAll().get(2).getAuthors());
        System.out.println("books through author : "+authorRepository.findAll().get(0).getBooks());
    }

    private Book givenBook(String name){
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }
    private Author givenAuthor(String name){
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

}