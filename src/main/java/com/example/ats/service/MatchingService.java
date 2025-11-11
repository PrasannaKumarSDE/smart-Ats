package com.example.ats.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.ats.entity.Candidate;
import com.example.ats.entity.Job;

@Service
public class MatchingService {

    // Simple rule + overlap score: (skill overlap * 70) + exp fit * 30
    public double score(Job job, Candidate c) {
        List<String> jobSkills = job.getSkills();
        List<String> candSkills = c.getSkills();
        Set<String> js = new HashSet<>();
        for (String s : jobSkills) {
			js.add(s.toLowerCase().trim());
		}
        int overlap = 0;
        for (String s : candSkills) {
			if (js.contains(s.toLowerCase().trim())) {
				overlap++;
			}
		}
        double skillScore = jobSkills.isEmpty()? 0 : (overlap * 1.0 / jobSkills.size()) * 70.0;

        double expScore = 0;
        if (job.getMinExp()!=null && job.getMaxExp()!=null && c.getTotalExp()!=null) {
            int e = c.getTotalExp();
            if (e >= job.getMinExp() && e <= job.getMaxExp()) {
				expScore = 30.0;
			} else if (e >= job.getMinExp()-1 && e <= job.getMaxExp()+1) {
				expScore = 20.0;
			} else {
				expScore = 10.0;
			}
        }
        return Math.round((skillScore + expScore) * 100.0) / 100.0;
    }
}
