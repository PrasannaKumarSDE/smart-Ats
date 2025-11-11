package com.example.ats.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity @Table(name="jobs")
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    @Column(name="min_exp") private Integer minExp;
    @Column(name="max_exp") private Integer maxExp;
    @Column(name="skills_json", columnDefinition="TEXT")
    private String skillsJson;
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(name="created_at")
    private Instant createdAt = Instant.now();

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getMinExp() {
		return minExp;
	}
	public void setMinExp(Integer minExp) {
		this.minExp = minExp;
	}
	public Integer getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(Integer maxExp) {
		this.maxExp = maxExp;
	}
	public String getSkillsJson() {
		return skillsJson;
	}
	public void setSkillsJson(String skillsJson) {
		this.skillsJson = skillsJson;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	@Transient
    public List<String> getSkills(){
        return JsonUtil.readList(skillsJson);
    }
    public void setSkills(List<String> skills){ this.skillsJson = JsonUtil.writeList(skills); }

    // getters/setters...
    // (Generate all getters & setters)
}
