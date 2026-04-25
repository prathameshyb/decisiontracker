package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.entities.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();

    public void addUsers(User user) throws Exception;

    void updateUser(User user) throws Exception;
}
