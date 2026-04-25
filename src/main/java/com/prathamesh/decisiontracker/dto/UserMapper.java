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

    public User toEntity(UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUserName());
        return user;
    }

}
