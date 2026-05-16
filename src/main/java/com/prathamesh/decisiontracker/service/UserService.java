package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> getUsers();

    public void addUser(UserDTO newUserDTO) throws Exception;

    void updateUser(UserDTO userDTO) throws Exception;

    void deleteUser(Integer userId) throws Exception;
}
