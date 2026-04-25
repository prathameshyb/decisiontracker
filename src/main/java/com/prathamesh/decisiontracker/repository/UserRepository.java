package com.prathamesh.decisiontracker.repository;

import com.prathamesh.decisiontracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
boolean existsByUserName(String UserName);
}
