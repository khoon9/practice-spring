package com.example.bookmanager.domain.listener;

import com.example.bookmanager.domain.UserHistory;
import com.example.bookmanager.domain.User;
import com.example.bookmanager.repository.UserHistoryRepository;
import com.example.bookmanager.support.BeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void prePersistAndUpdate(Object o){
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user =  (User) o;
        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);
        userHistory.setHomeAddress(user.getHomeAddress());
        userHistory.setCompanyAddress(user.getCompanyAddress());

        userHistoryRepository.save(userHistory);
    }
}
