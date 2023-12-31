package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Address;
import com.example.bookmanager.domain.Gender;
import com.example.bookmanager.domain.UserHistory;
import com.example.bookmanager.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;


@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    void saveSample(){
        for(int i=0; i<5; i++){
            User user = new User();
            user.setName("sample"+Integer.toString((Integer)i));
            user.setEmail("sample"+Integer.toString((Integer)i)+"@naver.com");
            userRepository.save(user);
        }
    }

    @Test
    @Transactional
    void crud() { // create, read, update, delete
//        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example02@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "sophia", "example03@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "james", "example04@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        saveSample();

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

        Example<User> example = Example.of(new User("se", "01"), matcher);
//        Example<Users> example = Example.of(new Users(null, "se", "01@naver.com", null, null));

        userRepository.findAll(example).forEach(System.out::println);

//        usersRepository.findAll().forEach(System.out::println);

//        System.out.println(exists);

//        System.out.println(count);

//        users.forEach(System.out::println);
//        System.out.println(users);

//        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void saveAndUpdateTest(){
//        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        saveSample();

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("sehun-update@naver.com");

        userRepository.save(user);
    }

    @Test
    void select(){
        // 데이터 표본 입력
//        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example02@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example03@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "james", "example04@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example05@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        saveSample();

        // 검색대상이 유니크하다는 전제하에 검색한 결과
        System.out.println("findByEmail : " + userRepository.findByEmail("example01@naver.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("example01@naver.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("example01@naver.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("example01@naver.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("example01@naver.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("example01@naver.com"));

        // 사용자 편의에 의한 접두어를 사용한 모습
        System.out.println("findUsersByEmail : " + userRepository.findUserByEmail("example01@naver.com"));
        System.out.println("findSomethingByEmail : " + userRepository.findSomethingByEmail("example01@naver.com"));

        // 접두어 키워드를 사용한 모습
        System.out.println("findFirst1ByName : ");
        userRepository.findFirst2ByName("dennis").forEach(System.out::println);
        System.out.println("findTop1ByName : ");
        userRepository.findTop2ByName("dennis").forEach(System.out::println);

        // 인식되지 않은 접두어 "Last2" 가 무시되어, findByName 쿼리 메소드로 인식되었다.
        System.out.println("findLast2ByName : ");
        userRepository.findLast2ByName("dennis").forEach(System.out::println);

        // where 조건 설명
        // And Or , After, Before
        System.out.println("findByEmailAndName : ");
        userRepository.findByEmailAndName("example01@naver.com", "sehun").forEach(System.out::println);
        System.out.println("findByEmailOrName : ");
        userRepository.findByEmailOrName("example01@naver.com", "dennis").forEach(System.out::println);

        System.out.println("findByCreatedAtAfter : ");
        userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)).forEach(System.out::println);
        System.out.println("findByIdAfter : ");
        userRepository.findByIdAfter(4L).forEach(System.out::println);

        System.out.println("findByCreatedAtGreaterThan : ");
        userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)).forEach(System.out::println);
        System.out.println("findByCreatedAtGreaterThanEqual : ");
        userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)).forEach(System.out::println);

        System.out.println("findByCreatedAtBetween : ");
        userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)).forEach(System.out::println);
        System.out.println("findByIdBetween : ");
        userRepository.findByIdBetween(1L, 3L).forEach(System.out::println);
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : ");
        userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L).forEach(System.out::println);

        System.out.println("findByIdIsNotNull : ");
        userRepository.findByIdIsNotNull().forEach(System.out::println);

        System.out.println("findByIdIsNotNull : ");
        userRepository.findByIdIsNotNull().forEach(System.out::println);

        // 여기선 Empty 거 문자열의 empty 가 아니라, collector 의 empty 을 말한다.
        // 많이 사용하진 않는다.
        // System.out.println("findByAddressIsNotEmpty : ");
        // usersRepository.findByAddressIsNotEmpty().forEach(System.out::println);

        // In
        System.out.println("findByNameIn : ");
        userRepository.findByNameIn(Lists.newArrayList("sehun", "dennis")).forEach(System.out::println);

        // 문자열 like 검색. StartingWith, EndingWith, Contains
        System.out.println("findByNameStartingWith : ");
        userRepository.findByNameStartingWith("se").forEach(System.out::println);
        System.out.println("findByNameEndingWith : ");
        userRepository.findByNameEndingWith("eh").forEach(System.out::println);
        System.out.println("findByNameContains : ");
        userRepository.findByNameContains("un").forEach(System.out::println);
        // % 은 방향. ~% 은 ~로 시작. %~은 ~로 끝. %~% 은 ~을 포함
        System.out.println("findByNameLike : ");
        userRepository.findByNameLike("%ehu%").forEach(System.out::println);

    }

    @Test
    void pagingAndSortingTest(){
        // 데이터 표본 입력
//        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example02@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example03@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "james", "example04@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example05@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        saveSample();

        // 정렬 테스트
        System.out.println("findTop1ByName : ");
        userRepository.findTop1ByName("dennis").forEach(System.out::println);
        // OrderByIdDesc
        System.out.println("findTop1ByNameOrderByIdDesc : ");
        userRepository.findTop1ByNameOrderByIdDesc("dennis").forEach(System.out::println);

        System.out.println("findFirst1ByNameOrderByIdDescEmailAsc : ");
        userRepository.findFirst1ByNameOrderByIdDescEmailAsc("dennis").forEach(System.out::println);
        // Sort 활
        System.out.println("findFirstByName With Sort Params  : ");
        userRepository.findFirstByName("dennis", Sort.by(Sort.Order.desc("id"))).forEach(System.out::println);

        System.out.println("findFirstByName With Sort Params  : ");
        userRepository.findFirstByName("dennis", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))).forEach(System.out::println);

        System.out.println("findByName With Paging  : "+ userRepository.findByName("dennis", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println("findByName With Paging  : "+ userRepository.findByName("dennis", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))));
    }

    @Test
    void   insertAndUpdateTest(){
        User user = new User();
        user.setName("sehun");
        user.setEmail("sehun@naver.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("ssssehun");

        userRepository.save(user2);
    }
    @Test
    void enumTest(){
//        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        saveSample();
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        //usersRepository.findAll().forEach(System.out::println);
        // raw record 을 확인해 보았으나, 0이 반환되었다.
        // 이는
        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    @Transactional
    void listenerTest(){
//        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example02@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example03@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "james", "example04@naver.com", LocalDateTime.now(), LocalDateTime.now()));
//        usersRepository.save(new Users(null, "dennis", "example05@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        saveSample();

        User user = new User();
        user.setEmail("sehun@naver.com");
        user.setName("sehun");

        // insert 발생
        userRepository.save(user);

        // search 발생
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("seeeehun");
        // update
        userRepository.save(user2);
        // delete
        userRepository.deleteById(4L);

    }
    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("sehun@naver.com");
        user.setName("sehun");
        userRepository.save(user);

        // setCreated 와 setUpdated 을 계속 반복해서 사용하는 경우
        // don't repeatable 사항에 대해 위반? 하며
        // 개발자가 실수로 해당 코드가 다르게 작성할 수도 있다.
        // 그렇기에 prePersistAt 와 preUpdatedAt 을 사용한다.

        System.out.println(userRepository.findById(1L).orElseThrow(RuntimeException::new));

    }
    @Test
    void preUpdateTest(){
//        usersRepository.save(new Users(null, "sehun", "example01@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        saveSample();

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is : "+ user);

        user.setName("sehuuuuun");
        userRepository.save(user);

        // 여기서 쓰인 .get(0) 은 영속성의 캐시를 이용한 것이다.
        // updateAt 이 업데이트에 따라 수정된 결과를 볼 수 있다.
        System.out.println("to-be : "+ userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("sehun@naver.com");
        user.setName("sehun");

        userRepository.save(user);

        user.setName("sehunnnnnn");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("sehun");
        user.setEmail("sehun@naver.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("sehun01");
        userRepository.save(user);


        user.setEmail("sehun01@naver.com");
        userRepository.save(user);

        List<UserHistory> userHistories = userRepository.findByEmail("sehun01@naver.com").getUserHistories();

        userHistories.forEach(System.out::println);

        System.out.println("UserHistory.getUser() : "+userHistoryRepository.findAll().get(0).getUser());

    }

    @Test
    void  embedTest(){
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시", "강남구", "강남대로 364 미왕빌딩", "06241"));
        user.setCompanyAddress(new Address("서울시", "성동구", "성수이로 113 제강빌딩", "04794"));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("joshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());

        userRepository.save(user2);

        entityManager.clear();

        userRepository.findAll().forEach(System.out::println);
//        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRawRecord().forEach(a-> System.out.println(a.values()));
    }
}