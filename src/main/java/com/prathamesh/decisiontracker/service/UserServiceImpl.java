package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.UserDTO;
import com.prathamesh.decisiontracker.dto.UserMapper;
import com.prathamesh.decisiontracker.entities.User;
import com.prathamesh.decisiontracker.exception.DuplicateEntryException;
import com.prathamesh.decisiontracker.exception.ResourceNotFoundException;
import com.prathamesh.decisiontracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User>users =  userRepository.findAll();
           return users.stream().map(userMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public void addUser(UserDTO newUserDTO) throws Exception {
        User newUser = userMapper.toEntity(newUserDTO);
        if(userRepository.existsByUserName(newUser.getUserName())) throw new DuplicateEntryException("Username must be unique");
        userRepository.save(newUser);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws Exception {
        User updateUser = userMapper.toEntity(userDTO);
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
