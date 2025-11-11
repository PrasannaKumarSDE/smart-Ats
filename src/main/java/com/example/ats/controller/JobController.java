package com.example.ats.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ats.entity.Job;
import com.example.ats.repo.JobRepository;

@RestController @RequestMapping("/api/jobs")
@CrossOrigin
public class JobController {
    private final JobRepository repo;
    public JobController(JobRepository repo){ this.repo = repo; }

    @PostMapping
    public Job create(@RequestBody Job job){ return repo.save(job); }

    @GetMapping
    public List<Job> all(){ return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Job> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @RequestBody Job j){
        return repo.findById(id).map(ex -> {
            ex.setTitle(j.getTitle());
            ex.setLocation(j.getLocation());
            ex.setMinExp(j.getMinExp());
            ex.setMaxExp(j.getMaxExp());
            ex.setSkills(j.getSkills());
            ex.setDescription(j.getDescription());
            return ResponseEntity.ok(repo.save(ex));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
