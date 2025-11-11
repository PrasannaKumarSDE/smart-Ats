package com.example.ats.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ats.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("select c from Candidate c where lower(c.parsedText) like lower(concat('%', ?1, '%')) " +
           "or lower(c.skillsJson) like lower(concat('%', ?1, '%'))")
    List<Candidate> searchText(String q);
}
