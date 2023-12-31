package com.example.bookmanager.service;

import com.example.bookmanager.domain.Gender;
import com.example.bookmanager.domain.User;
import com.example.bookmanager.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void entityManagerTest(){
        // 여기서 entityManager.createQuery("select u from Users u").getResultList() 에서
        // 사용된 sql 은 명확히 말하자면 jpa 의 sql 문법이라고 해도 무방하다
        // 실질적인 기능은 findAll 와 유사한데, 이는 jpa 의 쿼리 메소드가 직접적인 entity manager 의 사용 없이도
        // 사용자가 기능을 수월하기 사용할 수 있도록 렙핑을 한 것이라 봐도 무방하다.
        // 즉, 기존의 jpa 쿼리 메소드로 작성할 수 없는 쿼리는 사용자가 entity manager 로 직접 작성을 하면 된다는 것이다.
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }
    @Test
    @Transactional
    void cacheFindTest(){
        givenUsers("sehun01@naver.com");
        givenUsers("sehun02@naver.com");

        System.out.println(userRepository.findAll().get(0));
    }

    void givenUsers(String email){
        User user = new User();
        user.setName("sehun");
        user.setEmail(email);
        user.setGender(Gender.MALE);
        userRepository.save(user);
    }
    @Test
    @Transactional
    void cacheFindTest2(){
        User user = new User();
        user.setName("sehuuuuun");
        userRepository.save(user);

//        usersRepository.flush();

        System.out.println("-------------");

        user.setEmail("sehuuuuun@naver.com");
        userRepository.save(user);

//        usersRepository.flush();

//        System.out.println(">>> 1 : " + usersRepository.findById(1L).get());
//
////        usersRepository.flush();
//
//        System.out.println(">>> 2 : " + usersRepository.findById(1L).get());

        System.out.println(userRepository.findAll());
    }
}
