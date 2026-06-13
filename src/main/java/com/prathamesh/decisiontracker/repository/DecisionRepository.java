package com.prathamesh.decisiontracker.repository;

import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface DecisionRepository extends JpaRepository<Decision, Integer> {
    Boolean existsByTitle(String Title);

    @Query("SELECT d FROM Decision d WHERE d.score >= :minimum_score_for_best_decision")
    List<Decision> getBestDecisions(@Param("minimum_score_for_best_decision") Integer minimumScoreForBestDecision);

    @Query("SELECT d.decisionId, d.title, d.description, d.decisionDate, d.reviewAfterDays from Decision d")
    List<Object[]> getDecisionReviewDetails();

}
