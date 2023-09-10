package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.MiddleOfBookAndAuthor;
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

    @Autowired
    private MiddleOfBookAndAuthorRepository middleOfBookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest(){
        Book book01 = givenBook("책01");
        Book book02 = givenBook("책02");
        Book book03 = givenBook("개발책01");
        Book book04 = givenBook("개발책02");

        Author author01 = givenAuthor("sehun");
        Author author02 = givenAuthor("steve");

        MiddleOfBookAndAuthor middleOfBookAndAuthor01 = givenMiddleOfBookAndAuthor(book01, author01);
        MiddleOfBookAndAuthor middleOfBookAndAuthor02 = givenMiddleOfBookAndAuthor(book02, author02);
        MiddleOfBookAndAuthor middleOfBookAndAuthor03 = givenMiddleOfBookAndAuthor(book03, author01);
        MiddleOfBookAndAuthor middleOfBookAndAuthor04 = givenMiddleOfBookAndAuthor(book03, author02);
        MiddleOfBookAndAuthor middleOfBookAndAuthor05 = givenMiddleOfBookAndAuthor(book04, author01);
        MiddleOfBookAndAuthor middleOfBookAndAuthor06 = givenMiddleOfBookAndAuthor(book04, author02);

//        book01.addAuthor(author01);
//        book02.addAuthor(author02);
//        book03.addAuthor(author01, author02);
//        book04.addAuthor(author01, author02);
//
//        author01.addBook(book01,book03,book04);
//        author02.addBook(book02,book03,book04);

        book01.addMiddleOfBookAndAuthor(middleOfBookAndAuthor01);
        book02.addMiddleOfBookAndAuthor(middleOfBookAndAuthor02);
        book03.addMiddleOfBookAndAuthor(middleOfBookAndAuthor03,middleOfBookAndAuthor04);
        book04.addMiddleOfBookAndAuthor(middleOfBookAndAuthor05,middleOfBookAndAuthor06);

        author01.addMiddleOfBookAndAuthor(middleOfBookAndAuthor01,middleOfBookAndAuthor03,middleOfBookAndAuthor05);
        author02.addMiddleOfBookAndAuthor(middleOfBookAndAuthor02,middleOfBookAndAuthor04,middleOfBookAndAuthor06);

        bookRepository.saveAll(Lists.newArrayList(book01,book02,book03,book04));
        authorRepository.saveAll(Lists.newArrayList(author01,author02));

//        System.out.println("authors through book : "+bookRepository.findAll().get(2).getAuthors());
//        System.out.println("books through author : "+authorRepository.findAll().get(0).getBooks());

        bookRepository.findAll().get(2).getMiddleOfBookAndAuthors().forEach(o-> System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0).getMiddleOfBookAndAuthors().forEach(o-> System.out.println(o.getBook()));

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
    private MiddleOfBookAndAuthor givenMiddleOfBookAndAuthor(Book book, Author author){
        MiddleOfBookAndAuthor middleOfBookAndAuthor = new MiddleOfBookAndAuthor();
        middleOfBookAndAuthor.setBook(book);
        middleOfBookAndAuthor.setAuthor(author);

        return middleOfBookAndAuthorRepository.save(middleOfBookAndAuthor);
    }

}