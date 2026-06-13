package com.prathamesh.decisiontracker.dto;

import com.prathamesh.decisiontracker.entities.Decision;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public BestDecisionDTO toBestDecisionDTO(Decision decision){
        BestDecisionDTO bestDecisionDTO = new BestDecisionDTO();
        bestDecisionDTO.setTitle(decision.getTitle());
        bestDecisionDTO.setDescription(decision.getDescription());
        bestDecisionDTO.setScore(decision.getScore());
        return bestDecisionDTO;
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

    public DecisionDueReviewDTO toDecisionDueReviewDTO(Object[] result, int differenceDays) {
        DecisionDueReviewDTO decisionDueReviewDTO = new DecisionDueReviewDTO();
        decisionDueReviewDTO.setDecisionId((int)result[0]);
        decisionDueReviewDTO.setTitle((String) result[1]);
        decisionDueReviewDTO.setDescription((String) result[2]);
        decisionDueReviewDTO.setPeriodOverDueDays(differenceDays);
        return decisionDueReviewDTO;
    }
}
