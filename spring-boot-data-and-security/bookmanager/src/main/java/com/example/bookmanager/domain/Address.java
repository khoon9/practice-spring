package com.example.bookmanager.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Embeddable 어노테이션을 할당함으로서, Entity 에서 필드로 선언할 수 있게 되었다.
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    // 단, 시 와 구는 String 이 아니라 enum type 로 관리할 경우 효과적이다
    private String city;    // 시
    private String district;        // 구
    @Column(name = "address_detail")
    private String detail;      // 상세주소
    private String zipCode;     // 우편번호
}
