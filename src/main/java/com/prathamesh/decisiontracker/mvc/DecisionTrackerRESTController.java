package com.prathamesh.decisiontracker.mvc;

import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.entities.User;
import com.prathamesh.decisiontracker.repository.DecisionRepository;
import com.prathamesh.decisiontracker.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DecisionTrackerRESTController {

    private final DecisionRepository decisionRepository;
    private final UserRepository userRepository;

    public DecisionTrackerRESTController(DecisionRepository decisionRepository, UserRepository userRepository){
        this.decisionRepository = decisionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/decisions")
    List<Decision> getDecisions(){
        return decisionRepository.findAll();
    }

    @PostMapping("/decision")
    Decision newDecision(@RequestBody Decision newDecision){
        return decisionRepository.save(newDecision);
    }
}
