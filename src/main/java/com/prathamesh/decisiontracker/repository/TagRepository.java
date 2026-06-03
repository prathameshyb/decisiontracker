package com.prathamesh.decisiontracker.repository;

import com.prathamesh.decisiontracker.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Boolean existsByTagName(String tagName);
}
