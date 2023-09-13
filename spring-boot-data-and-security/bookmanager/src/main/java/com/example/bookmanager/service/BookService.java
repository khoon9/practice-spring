package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

     @Transactional
     public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("jpa starting");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("sehun");

        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB commit 이 발생하지 않습니다.");
    }


}
