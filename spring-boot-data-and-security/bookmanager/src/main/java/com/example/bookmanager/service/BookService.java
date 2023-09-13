package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
//@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

     @Transactional(propagation = Propagation.REQUIRED)
     public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("jpa starting");

        bookRepository.save(book);

        try{
            authorService.putAuthor();
        }catch(RuntimeException e){
            System.out.println("authorService.putAuthor() 오류 확인");
        }

//        Author author = new Author();
//        author.setName("sehun");
//
//        authorRepository.save(author);

//        throw new RuntimeException("오류가 나서 DB commit 이 발생하지 않습니다.");
        throw new RuntimeException("오류가 발생. 트렌젝션은?");
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void get(Long id){
        System.out.println(">>> "+bookRepository.findById(id));
        System.out.println(">>> "+bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> "+bookRepository.findById(id));
        System.out.println(">>> "+bookRepository.findAll());

        entityManager.clear();
//        Book book = bookRepository.findById(id).get();
//        book.setName("바뀔까?");
//        bookRepository.save(book);
    }

}
