package com.example.ats.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ats.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {}
