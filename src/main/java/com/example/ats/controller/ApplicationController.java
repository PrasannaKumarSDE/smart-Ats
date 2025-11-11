package com.example.ats.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ats.dto.MoveStageRequest;
import com.example.ats.entity.Application;
import com.example.ats.entity.Candidate;
import com.example.ats.entity.Job;
import com.example.ats.entity.Stage;
import com.example.ats.repo.ApplicationRepository;
import com.example.ats.repo.CandidateRepository;
import com.example.ats.repo.JobRepository;
import com.example.ats.service.MatchingService;

@RestController @RequestMapping("/api/applications")
@CrossOrigin
public class ApplicationController {
    private final ApplicationRepository appRepo;
    private final JobRepository jobRepo;
    private final CandidateRepository candRepo;
    private final MatchingService matching;

    public ApplicationController(ApplicationRepository appRepo, JobRepository jobRepo,
                                 CandidateRepository candRepo, MatchingService matching) {
        this.appRepo = appRepo; this.jobRepo = jobRepo; this.candRepo = candRepo; this.matching = matching;
    }

    @PostMapping
    public ResponseEntity<Application> create(@RequestBody Application in) {
        if (!jobRepo.existsById(in.getJobId()) || !candRepo.existsById(in.getCandidateId()) || appRepo.existsByJobIdAndCandidateId(in.getJobId(), in.getCandidateId())) {
			return ResponseEntity.badRequest().build();
		}
        in.setStage(Stage.APPLIED);
        Job job = jobRepo.findById(in.getJobId()).orElseThrow();
        Candidate c = candRepo.findById(in.getCandidateId()).orElseThrow();
        in.setScore(matching.score(job, c));
        return ResponseEntity.ok(appRepo.save(in));
    }

    @PostMapping("/{id}/move")
    public ResponseEntity<Application> move(@PathVariable Long id, @RequestBody MoveStageRequest req){
        return appRepo.findById(id).map(a -> {
            a.setStage(req.getStage());
            a.setNote(req.getNote());
            return ResponseEntity.ok(appRepo.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/job/{jobId}")
    public List<Application> byJob(@PathVariable Long jobId){
        List<Application> list = appRepo.findByJobId(jobId);
        list.sort(Comparator.comparing(Application::getScore, Comparator.nullsLast(Comparator.reverseOrder())));
        return list;
    }

    @GetMapping("/job/{jobId}/suggest")
    public ResponseEntity<List<Application>> suggest(@PathVariable Long jobId){
        var job = jobRepo.findById(jobId);
        if(job.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
        var all = candRepo.findAll();
        var apps = all.stream().map(c -> {
            Application a = new Application();
            a.setJobId(jobId);
            a.setCandidateId(c.getId());
            a.setStage(Stage.SCREENING);
            a.setNote("Auto-suggested");
            a.setScore(matching.score(job.get(), c));
            return a;
        }).sorted(Comparator.comparing(Application::getScore, Comparator.nullsLast(Comparator.reverseOrder())))
          .limit(10).toList();
        return ResponseEntity.ok(apps);
    }
}
