package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    // 스프링 데이타 Jpa 가 지원해주는 부분 <Entity Type, PK Type>
    // Jpa 관련된 많은 인터페이스를 사용할 수 있게 된다.
}
