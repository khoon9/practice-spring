package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Address;
import com.example.bookmanager.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // 스프링 데이타 Jpa 가 지원해주는 부분 <Entity Type, PK Type>
    // Jpa 관련된 많은 인터페이스를 사용할 수 있게 된다.

    // 반환 타입을 단일 객체로 할 경우, 중복검색된 데이터에 대해 오류가 발생한다.
    List<Users> findByName(String name);
    List<Users> findUsersByName(String name);
    List<Users> findUsersByNameIs(String name);
    List<Users> findUsersByNameEquals(String name);

    Users findByEmail(String email);

    Users getByEmail(String email);

    Users readByEmail(String email);

    Users queryByEmail(String email);

    Users searchByEmail(String email);

    Users streamByEmail(String email);

    Users findUsersByEmail(String email);

    // Something 라는 인식 불가 대상에 대해선 무시되어 findByEmail 쿼리 메소드로 구동함
    Users findSomethingByEmail(String email);
    List<Users> findFirst2ByName(String name);
    List<Users> findTop2ByName(String name);
    // Last2 라는 인식 불가 대상에 대해선 무시되어 findByName 쿼리 메소드로 구동함
    List<Users> findLast2ByName(String name);

    // Email 와 Name 모두 일치
    List<Users> findByEmailAndName(String email, String name);
    // Email 또는 Name 중에 하나라도 일치
    List<Users> findByEmailOrName(String email, String name);
    // After 은 ~ 보다 큰 값을 찾고, Before 은 ~ 보다 작은 값을 찾는다.
    // 단, 가독성을 위해 날짜에서만 사용하는 걸 권장. ~ 이후는 ~ 이상과 동일한 의미로 쓰였다.
    List<Users> findByCreatedAtAfter(LocalDateTime createdAt);
    List<Users> findByIdAfter(Long id);
    // GreatThan : ~ 보다 큰 값을 찾는다. GreaterThanEqual : ~ 보다 크거나 같은 값을 찾는다.
    List<Users> findByCreatedAtGreaterThan(LocalDateTime createdAt);
    List<Users> findByCreatedAtGreaterThanEqual(LocalDateTime createdAt);
    // Between : ~ 이상 이며 ~ 이하 ? <= and <= ?
    List<Users> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
    List<Users> findByIdBetween(Long id1, Long id2);
    // Between 와 동일한 동작
    List<Users> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    // IsNotNull
    List<Users> findByIdIsNotNull();

    // collector 에 대한 검사. 사용을 잘 안 함
    // List<Users> findByAddressIsNotEmpty();

    // In
    List<Users> findByNameIn(List<String> names);

    // 문자열에 대한 검색. like 동작 가능
    List<Users> findByNameStartingWith(String name);
    List<Users> findByNameEndingWith(String name);
    List<Users> findByNameContains(String name);
    List<Users> findByNameLike(String name);



}
