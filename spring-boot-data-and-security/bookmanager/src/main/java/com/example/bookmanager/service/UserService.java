package com.example.bookmanager.service;

import com.example.bookmanager.domain.Users;
import com.example.bookmanager.repository.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // Transactional 은 일반적으로 서비스 패키지에 생성을 많이 함

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsersRepository usersRepository;

    // 해당 메소드가 실행이 끝난 후, 메소드 내부에 생성된 비영속 entity 는
    // 영속화되지 않고 가비지 컬렉터를 통해서 사라지는 데이터가 된다.
    @Transactional
    public void put(){
        Users users = new Users();
        users.setName("newUser");
        users.setEmail("newUser@naver.com");

//        usersRepository.save(users);
        // users entity 가 영속성 상태로 돌입
        // 영속성 컨텍스트가 가지고 있는 entity 객체는 처음 컨텍스트에 로드를 할 때, entity 정보를 일종의 스냅샷으로 복사를 해서 가지고 있는다
        // 그리고 변경내용을 실제로 flush 해서 반영하는 시점에
        // 해당 스냅샷과 현재 entity 의 값을 일일이 비교를 해서 변경된 내용이 있다면 추가적으로 코드가 없다고 하더라도 DB 에 변경된 내용을 반영해준다.
        entityManager.persist(users);


        // 원래 영속화되었던 객체를 분리(detached)해서 영속성 컨텍스트 밖으로 꺼내는 것을 말한다.
        // 이러한 준영속화(detach) 상태에 대한 메소드는 jpa repository 에서 제공하기 않는다.
        // entityManager 에서만 제공한다.
        // 이럴 경우 향후 더티체크가 발생하지 않는다.
        entityManager.detach(users);



        // 영속석 상태인 객체가 더티 체크에 의해 자동으로 update 쿼리로 실핼된다.
        users.setName("newUserAfterPersist");

        // 준 영속화 상태인 entity 이더라도 entituManager 의 merge 메소드를 통해
        // 데이터 반영이 일어나게 된다.
        entityManager.merge(users);

        // jpa repository 의 save 을 통해 entityManager 의 persist 와 merge 을 수행할 수 있기에 개발자의 방식대로 하면 된다.

        // 또한, detached 상태에 들어갈 수 있는 명령어로는
        // clear, close 도 사용할 수 있으나 이는 좀 더 파괴적인 방법이다.
        // clear 을 사용할 경우 기존에 반영하려고 했었던, 컨텍스트에 반영되었던 변경 건들도 모두 drop 하게 된다.
//        entityManager.flush();
        entityManager.clear();

        // 만약 clear 을 사용하게 된다면 clear 메소드 호출 이전에 반드시 flush 메소드를 호출해서
        // 변경내역들을 일단 모두 반영하도록 하기를 권고한다.

        // remove 을 사용하게 되면 해당 entity 는 더이상 사용할 수 없게 된다.
        Users users1 = usersRepository.findById(1L).get();
        entityManager.remove(users1);
    }


}
