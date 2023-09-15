package com.example.bookmanager.repository;

import com.example.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {
    // 스프링 데이타 Jpa 가 지원해주는 부분 <Entity Type, PK Type>
    // Jpa 관련된 많은 인터페이스를 사용할 수 있게 된다.

    // 반환 타입을 단일 객체로 할 경우, 중복검색된 데이터에 대해 오류가 발생한다.
    List<User> findByName(String name);
    List<User> findUserByName(String name);
    List<User> findUserByNameIs(String name);
    List<User> findUserByNameEquals(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    // Something 라는 인식 불가 대상에 대해선 무시되어 findByEmail 쿼리 메소드로 구동함
    User findSomethingByEmail(String email);
    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);
    // Last2 라는 인식 불가 대상에 대해선 무시되어 findByName 쿼리 메소드로 구동함
    List<User> findLast2ByName(String name);

    // Email 와 Name 모두 일치
    List<User> findByEmailAndName(String email, String name);
    // Email 또는 Name 중에 하나라도 일치
    List<User> findByEmailOrName(String email, String name);
    // After 은 ~ 보다 큰 값을 찾고, Before 은 ~ 보다 작은 값을 찾는다.
    // 단, 가독성을 위해 날짜에서만 사용하는 걸 권장. ~ 이후는 ~ 이상과 동일한 의미로 쓰였다.
    List<User> findByCreatedAtAfter(LocalDateTime createdAt);
    List<User> findByIdAfter(Long id);
    // GreatThan : ~ 보다 큰 값을 찾는다. GreaterThanEqual : ~ 보다 크거나 같은 값을 찾는다.
    List<User> findByCreatedAtGreaterThan(LocalDateTime createdAt);
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime createdAt);
    // Between : ~ 이상 이며 ~ 이하 ? <= and <= ?
    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
    List<User> findByIdBetween(Long id1, Long id2);
    // Between 와 동일한 동작
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    // IsNotNull
    List<User> findByIdIsNotNull();

    // collector 에 대한 검사. 사용을 잘 안 함
    // List<Users> findByAddressIsNotEmpty();

    // In
    List<User> findByNameIn(List<String> names);

    // 문자열에 대한 검색. like 동작 가능
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
    List<User> findByNameLike(String name);

    List<User> findTop1ByName(String name);

    // 역순으로 만들기 후 하나 가져오기
    // Asc 는 정순, Desc 는 역순
    List<User> findTop1ByNameOrderByIdDesc(String name);
    // 길어지면 점점 가독성이 떨어진다. 자유도가 떨어진다
    // 네이밍 컨벤션 기반 정렬
    List<User> findFirst1ByNameOrderByIdDescEmailAsc(String name);
    // 코드 가독성, 자유도 좋음
    // Sort 파라미터 기반 정렬
    List<User> findFirstByName(String name, Sort sort);

    // Pageable : page 에 대한 요청 값, page : paging 에 대한 응답 값
    Page<User> findByName(String name, Pageable pageable);

    // return 타입은 맵핑을 이룬다면 어떤 것이든 가능하기 때문에
    // nativeQuery 을 하여 value 을 그대로 수행. 그 걀과 값이 Map 에 저장됨
    @Query(value = "select * from user limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();

    @Query(value = "select * from user", nativeQuery = true)
    List<Map<String, Object>> findAllRawRecord();
}


