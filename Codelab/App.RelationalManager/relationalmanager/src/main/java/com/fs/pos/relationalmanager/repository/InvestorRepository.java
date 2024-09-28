package com.fs.pos.relationalmanager.repository;

import com.fs.pos.relationalmanager.entities.Investor;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {
    // Get all investors by user ID
    List<Investor> findByUserId(Long userId);
}