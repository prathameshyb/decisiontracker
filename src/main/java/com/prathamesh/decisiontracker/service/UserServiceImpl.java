package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.*;
import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.entities.User;
import com.prathamesh.decisiontracker.exception.DuplicateEntryException;
import com.prathamesh.decisiontracker.exception.ResourceNotFoundException;
import com.prathamesh.decisiontracker.repository.DecisionRepository;
import com.prathamesh.decisiontracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final DecisionService decisionService;

    private final DecisionMapper decisionMapper;

    private final DecisionRepository decisionRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, DecisionService decisionService, DecisionMapper decisionMapper, DecisionRepository decisionRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.decisionService = decisionService;
        this.decisionMapper = decisionMapper;
        this.decisionRepository = decisionRepository;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User>users =  userRepository.findAll();
           return users.stream().map(userMapper::toDTO).collect(Collectors.toList());

    }

    public void getDecisionsForUser(int userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        System.out.println(user.getDecisionList());
    }

    @Override
    public void setUserDecisions(int userId, List<Integer> decisionIds) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        for (Integer decisionId : decisionIds) {
            Decision decision = decisionRepository.findById(decisionId).orElseThrow(() -> new ResourceNotFoundException("Decision", decisionId));
            decision.setUser(user);
            decisionRepository.save(decision);
        }
    }

    @Override
    public UserDTO getUserById(Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        return userMapper.toDTO(user);
    }

    @Override
    public List<DecisionDTO> getUserDecisions(Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        List<DecisionDTO>userDecisions;
        userDecisions = (user.getDecisionList()).stream().map(decisionMapper::toDTO).toList();
        return userDecisions;
    }

    @Override
    public void addUser(CreateUserDTO newUserDTO) throws Exception {
        User newUser = userMapper.createUserDTOtoEntity(newUserDTO);
        if(userRepository.existsByUserName(newUser.getUserName())) throw new DuplicateEntryException("Username must be unique");
        userRepository.save(newUser);
    }

    @Override
    public void updateUser(UpdateUserDTO userDTO) throws Exception {
        User updateUser = userMapper.updateUserDTOtoEntity(userDTO);
        if(!userRepository.existsById(updateUser.getUserId())){
            throw new ResourceNotFoundException("User", userDTO.getUserId());
        }
        userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(Integer userId)throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        userRepository.delete(user);
    }
}
