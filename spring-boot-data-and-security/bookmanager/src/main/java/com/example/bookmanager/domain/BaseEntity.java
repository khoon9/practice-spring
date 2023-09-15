package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
// @MappedSuperclass : 해당 클래스의 필드를 상속받는 클래스의 컬럼으로 포함시켜주겠다는 것
// 즉,
@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {
    // 해당 컬럼의 기본 데이터를 설정. auditing 에 의해, 실질적으로는 data.sql 에서만 적용된다고 보면된다.
    // default now(6) 라고만 적을 경우 타입이 default 으로 지정되어 오류가 발생한다.
    // columnDefinition 이 받아온 문자열이 데이터 타입과 그대로 바꿔치기(치환) 되므로 타입을 먼저 명시하는 과정이 필요하다.
    @CreatedDate
    @Column(columnDefinition = "datetime(6) default now(6) comment '생성시간'", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(columnDefinition = "datetime(6) default now(6) comment '수정시간'", nullable = false)
    private LocalDateTime updatedAt;
}
