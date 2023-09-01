package com.example.restaurant.db;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// T extends MemoryDbEntity : MemoryDbEntity 을 상속받은 T
@Slf4j
abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements  MemoryDbRepository<T>{

    // 데이터 저장할 배열 리스트
    private final List<T> db = new ArrayList<>();
    // primary key. auto general 해줌
    private int index = 0;
    @Override
    public Optional<T> findById(int index) {
        // 필터링과 index id 로 가장 먼저 찾은 T 를 반환한다.
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();


        if(optionalEntity.isEmpty()){
            // db 에 데이터가 없는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;

        }else{
            // db 에 이미 데이터가 있는 경우
            // optionalEntity.get() : T
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            // Update : Delete & Add 발생
            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()){
            // optionalEntity 이 db 에서 발원된 것이기에 가능한 동작
            // 유의
            // remove 에서 Integer 정보는 무시된 것으로 보인다.
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> findAll() {
        return db;
    }
}
