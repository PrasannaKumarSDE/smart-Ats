package com.example.ats.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ats.entity.Candidate;
import com.example.ats.repo.CandidateRepository;

@RestController @RequestMapping("/api/search")
@CrossOrigin
public class SearchController {
    private final CandidateRepository repo;
    public SearchController(CandidateRepository repo){ this.repo = repo; }

    @GetMapping("/candidates")
    public List<Candidate> search(@RequestParam("q") String q){
        return repo.searchText(q);
    }
}
