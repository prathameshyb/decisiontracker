package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.CreateUserDTO;
import com.prathamesh.decisiontracker.dto.DecisionDTO;
import com.prathamesh.decisiontracker.dto.UpdateUserDTO;
import com.prathamesh.decisiontracker.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    void addUser(CreateUserDTO newUserDTO) throws Exception;

    void updateUser(UpdateUserDTO updateUserDTO) throws Exception;

    void deleteUser(Integer userId) throws Exception;

    void setUserDecisions(int userId, List<Integer>decisionIds ) throws Exception;

    UserDTO getUserById(Integer userId) throws Exception;

    List<DecisionDTO>getUserDecisions(Integer userId) throws Exception;
}
