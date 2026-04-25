package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.entities.User;
import com.prathamesh.decisiontracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUsers(User user) throws Exception {
        if(userRepository.existsByUserName(user.getUserName())) throw new Exception("Username must be unique");
        userRepository.save(user);
    }

    @Override
    public void updateUser(User updateUser) throws Exception {
        User user = userRepository.findById(updateUser.getUserId()).orElseThrow(() -> new Exception("User not found"));
        userRepository.save(updateUser);
    }
}
