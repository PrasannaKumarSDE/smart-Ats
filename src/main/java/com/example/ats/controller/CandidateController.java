package com.example.ats.controller;

import com.example.ats.entity.Candidate;
import com.example.ats.repo.CandidateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    private final CandidateRepository repo;

    public CandidateController(CandidateRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Candidate create(@RequestBody Candidate c) {
        return repo.save(c);
    }

    @GetMapping
    public List<Candidate> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> update(@PathVariable Long id, @RequestBody Candidate in) {
        return repo.findById(id).map(ex -> {
            ex.setName(in.getName());
            ex.setEmail(in.getEmail());
            ex.setPhone(in.getPhone());
            ex.setTotalExp(in.getTotalExp());
            ex.setSkills(in.getSkills());
            ex.setCurrentLocation(in.getCurrentLocation());
            return ResponseEntity.ok(repo.save(ex));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<Integer> score(@PathVariable Long id) {
        return repo.findById(id).map(c -> {
            int score = 0;
            List<String> skills = c.getSkills();
            if (skills != null) {
                for (String s : skills) {
                    if (List.of("java", "spring", "mysql", "rest", "html", "css").contains(s.toLowerCase())) {
                        score += 20;
                    }
                }
            }
            return ResponseEntity.ok(Math.min(score, 100));
        }).orElse(ResponseEntity.notFound().build());
    }
}
