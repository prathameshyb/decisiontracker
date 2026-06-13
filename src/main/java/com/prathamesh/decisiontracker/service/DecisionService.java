package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.*;

import java.util.List;

public interface DecisionService {

    List<DecisionDTO> getDecisions();

    DecisionDTO getDecisionById(int decisionId) throws Exception;

    void addDecisions(CreateDecisionDTO createDecisionDTO) throws Exception;

    void updateDecision(UpdateDecisionDTO updateDecisionDTO) throws Exception;

    void deleteDecision(Integer decisionId) throws Exception;

    void setDecisionTags(Integer decisionId, List<Integer> tagIds) throws Exception;

    List<TagDTO> getDecisionTags(Integer decisionId);

    List<BestDecisionDTO> getBestDecisions();

    List<DecisionDueReviewDTO> getDecisionsDueReview();

}
