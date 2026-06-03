package com.prathamesh.decisiontracker.dto;

import com.prathamesh.decisiontracker.entities.Decision;
import org.springframework.stereotype.Component;

@Component
public class DecisionMapper {
    public DecisionDTO toDTO(Decision decision){
        DecisionDTO decisionDTO = new DecisionDTO();
        decisionDTO.setDecisionId(decision.getDecisionId());
        decisionDTO.setTitle(decision.getTitle());
        decisionDTO.setScore(decision.getScore());
        decisionDTO.setReviewAfterDays(decision.getReviewAfterDays());
        decisionDTO.setDescription(decision.getDescription());
        decisionDTO.setDecisionDate(decision.getDecisionDate());
        decisionDTO.setExpectedOutcome(decision.getExpectedOutcome());
        decisionDTO.setActualOutCome(decision.getActualOutCome());

        return decisionDTO;
    }

    public Decision toEntity(DecisionDTO decisionDTO){
        Decision decision = new Decision();
        decision.setTitle(decisionDTO.getTitle());
        decision.setScore(decisionDTO.getScore());
        decision.setReviewAfterDays(decisionDTO.getReviewAfterDays());
        decision.setExpectedOutcome(decisionDTO.getExpectedOutcome());
        decision.setDescription(decisionDTO.getDescription());
        decision.setActualOutCome(decisionDTO.getActualOutCome());
        decision.setDecisionDate(decisionDTO.getDecisionDate());
        return decision;
    }

    public Decision toEntity(CreateDecisionDTO createDecisionDTO){
        Decision decision = new Decision();
        decision.setTitle(createDecisionDTO.getTitle());
        decision.setScore(createDecisionDTO.getScore());
        decision.setReviewAfterDays(createDecisionDTO.getReviewAfterDays());
        decision.setExpectedOutcome(createDecisionDTO.getExpectedOutcome());
        decision.setDescription(createDecisionDTO.getDescription());
        decision.setActualOutCome(createDecisionDTO.getActualOutCome());
        decision.setDecisionDate(createDecisionDTO.getDecisionDate());
        return decision;
    }

    public Decision toEntity(UpdateDecisionDTO updateDecisionDTO){
        Decision decision = new Decision();
        decision.setDecisionId(updateDecisionDTO.getDecisionId());
        decision.setTitle(updateDecisionDTO.getTitle());
        decision.setScore(updateDecisionDTO.getScore());
        decision.setReviewAfterDays(updateDecisionDTO.getReviewAfterDays());
        decision.setExpectedOutcome(updateDecisionDTO.getExpectedOutcome());
        decision.setDescription(updateDecisionDTO.getDescription());
        decision.setActualOutCome(updateDecisionDTO.getActualOutCome());
        decision.setDecisionDate(updateDecisionDTO.getDecisionDate());
        return decision;
    }
}
