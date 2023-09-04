package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Users;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


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
        usersRepository.save(new Users(null, "dennis", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));

//        Users users = usersRepository.getOne(3L);

//        List<Users> users = usersRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<Users> users = usersRepository.findAllById(Lists.newArrayList(1L,3L,5L));

//        Users user1 = new Users(null, "asd", "asd@naver.com", LocalDateTime.now(), LocalDateTime.now());
//        Users user2 = new Users(null, "zxc", "zxc@naver.com", LocalDateTime.now(), LocalDateTime.now());
//        usersRepository.saveAll(Lists.newArrayList(user1, user2));
//        List<Users> users = usersRepository.findAll();

//        Optional<Users> users = usersRepository.findById(1L);
//        Users users = usersRepository.findById(1L).orElse(null);

//        usersRepository.flush();

//        long count = usersRepository.count();

//        boolean exists = usersRepository.existsById(1L);


//        usersRepository.delete(usersRepository.findById(1L).orElseThrow(RuntimeException::new));

//        usersRepository.deleteById(1L);

//        usersRepository.deleteAll();
//        usersRepository.deleteAll(usersRepository.findAllById(Lists.newArrayList(1L, 3L)));
//        usersRepository.deleteInBatch(usersRepository.findAllById(Lists.newArrayList(1L, 3L)));
//        usersRepository.deleteAllInBatch();

//        // 가져오는 페이지의 번호는 1, 페이지 사이즈는 3 이라고 본다. 또한 페이지는 zero-base 이므로 0 부터 시작한다.
//        Page<Users> users = usersRepository.findAll(PageRequest.of(1,3));
//
//        System.out.println("page : "+users);
//        // 전체 객체 수
//        System.out.println("totalElements : " + users.getTotalElements());
//        // 전체 객체 수를 페이지 사이즈로 나누어 나온 값. 즉 전체 페이지 수를 의미한다.
//        System.out.println("totalPages : " + users.getTotalPages());
//        // 현재 가져온 레코드의 번호를 의미함. 즉, 가져온 페이지 수의 번호를 의미한다. 갯수 x 번호 o
//        // zero-base index 에서 1을 더하고 반환.
//        System.out.println("numberOfElements : " + users.getNumberOfElements());
//        // sort 을 반환. sort 상태가 아닌 경우에는 UNSORTED 을 반환한다.
//        System.out.println("sort : " + users.getSort());
//        // 페이징할 때 기준이 되는 사이즈를 의미한다.
//        System.out.println("size : " + users.getSize());
//
//        // 내부에 들어있는 유저에 대한 정보를 보기 위해서 사용한다.
//        users.getContent().forEach(System.out::println);


        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", contains());

        Example<Users> example = Example.of(new Users(null, "se", "01", null, null), matcher);
//        Example<Users> example = Example.of(new Users(null, "se", "01@naver.com", null, null));

        usersRepository.findAll(example).forEach(System.out::println);

//        usersRepository.findAll().forEach(System.out::println);

//        System.out.println(exists);

//        System.out.println(count);

//        users.forEach(System.out::println);
//        System.out.println(users);

//        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void saveAndUpdateTest(){
        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));

        Users users = usersRepository.findById(1L).orElseThrow(RuntimeException::new);
        users.setEmail("sehun-update@naver.com");

        usersRepository.save(users);
    }

    @Test
    void select(){
        // 데이터 표본 입력
        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "dennis", "example02@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "dennis", "example03@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "james", "example04@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        usersRepository.save(new Users(null, "dennis", "example05@naver.com", LocalDateTime.now(), LocalDateTime.now()));

        // 검색대상이 유니크하다는 전제하에 검색한 결과
        System.out.println("findByEmail : " + usersRepository.findByEmail("example01@naver.com"));
        System.out.println("getByEmail : " + usersRepository.getByEmail("example01@naver.com"));
        System.out.println("readByEmail : " + usersRepository.readByEmail("example01@naver.com"));
        System.out.println("queryByEmail : " + usersRepository.queryByEmail("example01@naver.com"));
        System.out.println("searchByEmail : " + usersRepository.searchByEmail("example01@naver.com"));
        System.out.println("streamByEmail : " + usersRepository.streamByEmail("example01@naver.com"));

        // 사용자 편의에 의한 접두어를 사용한 모습
        System.out.println("findUsersByEmail : " + usersRepository.findUsersByEmail("example01@naver.com"));
        System.out.println("findSomethingByEmail : " + usersRepository.findSomethingByEmail("example01@naver.com"));

        // 접두어 키워드를 사용한 모습
        System.out.println("findFirst1ByName : ");
        usersRepository.findFirst2ByName("dennis").forEach(System.out::println);
        System.out.println("findTop1ByName : ");
        usersRepository.findTop2ByName("dennis").forEach(System.out::println);

        // 인식되지 않은 접두어 "Last2" 가 무시되어, findByName 쿼리 메소드로 인식되었다.
        System.out.println("findLast2ByName : ");
        usersRepository.findLast2ByName("dennis").forEach(System.out::println);


    }
}