package com.prathamesh.decisiontracker.repository;

import com.prathamesh.decisiontracker.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByAdminName(String adminName);

}
