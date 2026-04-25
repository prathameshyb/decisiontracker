package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.entities.User;
import com.prathamesh.decisiontracker.repository.DecisionRepository;
import com.prathamesh.decisiontracker.repository.UserRepository;

import java.util.List;

public interface DecisionService  {


    public List<Decision>getDecisions();

    public void addDecisions(Decision decision) throws Exception;

    void updateDecision(Decision decision) throws Exception;
}
