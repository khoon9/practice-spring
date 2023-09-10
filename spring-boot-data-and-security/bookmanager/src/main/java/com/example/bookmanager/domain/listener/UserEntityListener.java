package com.example.bookmanager.domain.listener;

import com.example.bookmanager.domain.UserHistory;
import com.example.bookmanager.domain.Users;
import com.example.bookmanager.repository.UserHistoryRepository;
import com.example.bookmanager.support.BeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void prePersistAndUpdate(Object o){
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        Users users =  (Users) o;
        UserHistory userHistory = new UserHistory();
        userHistory.setName(users.getName());
        userHistory.setEmail(users.getEmail());
        userHistory.setUsers(users);

        userHistoryRepository.save(userHistory);
    }
}
