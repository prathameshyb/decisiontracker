package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.repository.DecisionRepository;
import com.prathamesh.decisiontracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionServiceImpl implements DecisionService{
    private final DecisionRepository decisionRepository;

   @Autowired
    public DecisionServiceImpl(DecisionRepository decisionRepository, UserRepository userRepository) {
        this.decisionRepository = decisionRepository;
    }


    @Override
    public List<Decision> getDecisions() {
        return decisionRepository.findAll();
    }

    @Override
    public void addDecisions(Decision decision) throws Exception {
       if(decisionRepository.existsByTitle(decision.getTitle())){
           throw new Exception("Title must be unique");
       }
        decisionRepository.save(decision);
    }

    @Override
    public void updateDecision(Decision decision) throws Exception {
        Decision updateDecision = decisionRepository.findById(decision.getDecisionId()).orElseThrow(()-> new Exception("Decision not found"));
        decisionRepository.save(decision);
    }


}
