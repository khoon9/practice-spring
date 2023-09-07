package com.example.bookmanager.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class MyEntityListener {
    // entity 에서는 this. 의 값이기 때문에 Object 을 확인할 수 있지만
    // 여기서는 그런 방법으로는 알 수 없기에 Object 로 받아야 한다.
    @PrePersist
    public void prePersist(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
    @PreUpdate
    public void preupdate(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
