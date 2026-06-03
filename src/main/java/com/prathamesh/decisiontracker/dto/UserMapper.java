package com.prathamesh.decisiontracker.dto;

import com.prathamesh.decisiontracker.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        return userDTO;
    }

    public User createUserDTOtoEntity(CreateUserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUserName());
        return user;
    }

    public User updateUserDTOtoEntity(UpdateUserDTO userDTO){
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        return user;
    }

}
