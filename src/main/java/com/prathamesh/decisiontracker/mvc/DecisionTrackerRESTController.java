package com.prathamesh.decisiontracker.mvc;

import com.prathamesh.decisiontracker.dto.DecisionDTO;
import com.prathamesh.decisiontracker.dto.UserDTO;
import com.prathamesh.decisiontracker.service.DecisionService;
import com.prathamesh.decisiontracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DecisionTrackerRESTController {

    @Autowired
    private final DecisionService decisionService;

    @Autowired
    private final UserService userService;



    public DecisionTrackerRESTController(DecisionService decisionService, UserService userService){
        this.decisionService = decisionService;
        this.userService = userService;
    }

    @GetMapping("/users")
    List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void newUser(@RequestBody UserDTO newUserDTO) throws Exception {
        userService.addUser(newUserDTO);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserDTO userDTO) throws Exception {
        userService.updateUser(userDTO);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUsers(@RequestBody List<Integer>userIds) throws Exception{
        for(Integer userId : userIds){
            userService.deleteUser(userId);
        }
    }

    @GetMapping("/decisions")
    List<DecisionDTO> getDecisions(){
        return decisionService.getDecisions();
    }

    @PostMapping("/decision")
    @ResponseStatus(HttpStatus.CREATED)
    public void newDecision(@RequestBody DecisionDTO newDecisionDTO) throws Exception {
        decisionService.addDecisions(newDecisionDTO);
    }

    @PutMapping("/decision")
    @ResponseStatus(HttpStatus.OK)
    public void updateDecision(@RequestBody DecisionDTO decisionDTO) throws Exception{
        decisionService.updateDecision(decisionDTO);
    }

    @DeleteMapping("/decisions")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDecisions(@RequestBody List<Integer> decisionIds) throws Exception{
        for(Integer decisionId : decisionIds){
            decisionService.deleteDecision(decisionId);
        }
    }
}
