package com.prathamesh.decisiontracker.repository;

import com.prathamesh.decisiontracker.entities.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionRepository extends JpaRepository<Decision, Integer> {
    boolean existsByTitle(String Title);

}
