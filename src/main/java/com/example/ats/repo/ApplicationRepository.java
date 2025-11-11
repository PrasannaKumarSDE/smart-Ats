package com.example.ats.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ats.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobId(Long jobId);
    boolean existsByJobIdAndCandidateId(Long jobId, Long candidateId);
}
