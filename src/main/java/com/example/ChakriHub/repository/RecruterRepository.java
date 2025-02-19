package com.example.ChakriHub.repository;

import com.example.ChakriHub.entity.recruter.Recruter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruterRepository extends JpaRepository<Recruter,Long> {
}
