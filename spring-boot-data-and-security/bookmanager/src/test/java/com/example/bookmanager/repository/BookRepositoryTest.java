package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import com.example.bookmanager.domain.User;
import com.example.bookmanager.repository.dto.BookNameAndCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void queryTest(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : "
        + bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                "jpa book 01",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)
        ));
        System.out.println("findByNameRecentry : "+ bookRepository.findByNameRecentry(
                "jpa book 01",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)
        ));
//        bookRepository.findBookNameAndCategory().forEach(tuple -> System.out.println(tuple.get(0)+ " : " + tuple.get(1)));
        bookRepository.findBookNameAndCategory().forEach(b -> System.out.println(b.getName()+ " : " + b.getCategory()));


        bookRepository.findBookNameAndCategory(PageRequest.of(1,1))
                .forEach(BookNameAndCategory -> System.out.println(BookNameAndCategory.getName()+ " : " + BookNameAndCategory.getCategory()));

        bookRepository.findBookNameAndCategory(PageRequest.of(0,1))
                .forEach(b -> System.out.println(b.getName()+ " : " + b.getCategory()));
    }



    @Test
    @Transactional
    void bookTest(){
        Book book = new Book();
        book.setName("bookname");
        book.setAuthorId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    void bookRelationTest(){
        givenBookAndReview();

        // 일반적으론, 인증정보를 통해서 users 값을 가져온다
        User user = userRepository.findByEmail("sehun@naver.com");

        System.out.println("User : "+ user);
        System.out.println("Review : " + user.getReviews().get(0));
        System.out.println("Book : "+ user.getReviews().get(0).getBook());
//        System.out.println("Publisher : "+users.getReviews().get(0).getBook().getPublisher());
    }

//    @Transactional
    @Test
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("Jpa starting");

        Publisher publisher = new Publisher();
        publisher.setName("sehun Co.");

        book.setPublisher(publisher);
        bookRepository.save(book);

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());

        System.out.println("book3-publisher : "+bookRepository.findById(1L).get().getPublisher());
        System.out.println("book : "+bookRepository.findById(1L).get());
        System.out.println("publisher : "+publisherRepository.findById(1L).get());
    }

    @Test
    void softDeleteTest(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(2L));
    }

    @Test
    void bookRemoveCascadeTest(){
        bookRepository.deleteById(1L);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());

        bookRepository.findAll().forEach(book-> System.out.println(book.getPublisher()));


    }


    private void givenBookAndReview(){
        givenReview(givenUsers(), givenBook(givenPublisher()));
    }

    private User givenUsers(){
        User user = new User("sehun", "sehun@naver.com");
        userRepository.save(user);
        return user;
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("책 이름");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("SH CO.");

        return publisherRepository.save(publisher);
    }

    private void givenReview(User user, Book book){
        Review review = new Review();
        review.setTitle("리뷰 제목");
        review.setContent("~~~리뷰 내용~~~");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

//        System.out.println(reviewRepository.save(review));

        reviewRepository.save(review);
    }
}