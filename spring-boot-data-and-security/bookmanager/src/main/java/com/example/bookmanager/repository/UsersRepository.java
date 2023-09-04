package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // 스프링 데이타 Jpa 가 지원해주는 부분 <Entity Type, PK Type>
    // Jpa 관련된 많은 인터페이스를 사용할 수 있게 된다.

    // 반환 타입을 단일 객체로 할 경우, 중복검색된 데이터에 대해 오류가 발생한다.
    List<Users> findByName(String name);

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
}
