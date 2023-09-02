package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Users;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Transactional
    void crud() { // create, read, update, delete
        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "dennis", "example02@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "sophia", "example03@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "james", "example04@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "dennis", "example05@naver.com", LocalDateTime.now(), LocalDateTime.now()));

        Optional<Users> users = usersRepository.findById(1L);
//        Users users = usersRepository.findById(1L).orElse(null);

//        Users user1 = new Users(null, "asd", "asd@naver.com", LocalDateTime.now(), LocalDateTime.now());
//        Users user2 = new Users(null, "zxc", "zxc@naver.com", LocalDateTime.now(), LocalDateTime.now());
//        usersRepository.saveAll(Lists.newArrayList(user1, user2));
//        List<Users> users = usersRepository.findAll();

//        Users users = usersRepository.getOne(3L);

//        List<Users> users = usersRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<Users> users = usersRepository.findAllById(Lists.newArrayList(1L,3L,5L));

        // users.forEach(System.out::println);
        System.out.println(users);
    }

}