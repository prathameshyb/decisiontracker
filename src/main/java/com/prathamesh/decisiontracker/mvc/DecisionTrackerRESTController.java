package com.prathamesh.decisiontracker.mvc;

import com.prathamesh.decisiontracker.dto.DecisionDTO;
import com.prathamesh.decisiontracker.dto.DecisionMapper;
import com.prathamesh.decisiontracker.dto.UserDTO;
import com.prathamesh.decisiontracker.dto.UserMapper;
import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.entities.User;
import com.prathamesh.decisiontracker.repository.DecisionRepository;
import com.prathamesh.decisiontracker.repository.UserRepository;
import com.prathamesh.decisiontracker.service.DecisionService;
import com.prathamesh.decisiontracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DecisionTrackerRESTController {

    @Autowired
    private final DecisionService decisionService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final DecisionMapper decisionMapper;

    @Autowired
    private final UserMapper userMapper;

    public DecisionTrackerRESTController(DecisionService decisionService, UserService userService,
                                         DecisionMapper decisionMapper, UserMapper userMapper){
        this.decisionService = decisionService;
        this.userService = userService;
        this.decisionMapper = decisionMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    List<UserDTO> getUsers(){
        List<User> users = userService.getUsers();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody UserDTO newUserDTO) throws Exception {
        User newUser = userMapper.toEntity(newUserDTO);
        userService.addUsers(newUser);
        return userMapper.toDTO(newUser);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UserDTO userDTO) throws Exception {
        User user = userMapper.toEntity(userDTO);
        user.setUserId(userDTO.getUserId());
        userService.updateUser(user);
        return userMapper.toDTO(user);
    }

    @GetMapping("/decisions")
    List<DecisionDTO> getDecisions(){
        List<Decision> decisions = decisionService.getDecisions();
        return decisions.stream().map(decisionMapper::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/decision")
    @ResponseStatus(HttpStatus.CREATED)
    DecisionDTO newDecision(@RequestBody DecisionDTO newDecisionDTO) throws Exception {
        Decision newDecision = decisionMapper.toEntity(newDecisionDTO);
        decisionService.addDecisions(newDecision);
        return decisionMapper.toDTO(newDecision);
    }

    @PutMapping("/decision")
    @ResponseStatus(HttpStatus.OK)
    public DecisionDTO updateDecision(@RequestBody DecisionDTO decisionDTO) throws Exception{
        Decision decision = decisionMapper.toEntity(decisionDTO);
        decision.setDecisionId(decisionDTO.getDecisionId());
        decisionService.updateDecision(decision);
        return decisionMapper.toDTO(decision);
    }
}
