package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.DecisionDTO;

import java.util.List;

public interface DecisionService {

    public List<DecisionDTO> getDecisions();

    public void addDecisions(DecisionDTO decisionDTO) throws Exception;

    void updateDecision(DecisionDTO decisionDTO) throws Exception;

    void deleteDecision(Integer decisionId) throws Exception;

}
