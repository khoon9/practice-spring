package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.UserEntityListener;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
//@Builder
@Entity
@EntityListeners(value = {UserEntityListener.class})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // DB 에서
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    // 임베드 표시
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "district", column = @Column(name = "home_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "company_city")),
            @AttributeOverride(name = "district", column = @Column(name = "company_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "company_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "company_zipCode"))
    })
    private Address companyAddress;


    // Jpa 에서는 해당 값이 존재하지 않으면 빈 리스트를 자동으로 넣어준다
    // 다만, Jpa 에서 Persist 을 하기 전에는 해당 값이 Null 이기 때문에 nullpoint exception 이 발생 할 수도 있다.
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
