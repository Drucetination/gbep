package com.gbep.userservice.services;

import com.gbep.userservice.model.User;
import com.gbep.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Long getLevelById(Integer userId) {
        return userRepository.getLevelById(userId);
    }

    public Long getNumbersOfGameById(Integer userId){
        return userRepository.getNumbersOfGameById(userId);
    }
    public void refreshUserStatus(Integer userId){
       userRepository.updateUserStatus(userId);
    }

}
