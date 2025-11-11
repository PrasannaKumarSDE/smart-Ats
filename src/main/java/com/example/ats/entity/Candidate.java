package com.example.ats.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity @Table(name="candidates")
public class Candidate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Column(name="total_exp") private Integer totalExp;
    @Column(name="skills_json", columnDefinition="TEXT")
    private String skillsJson;
    @Column(name="current_location") private String currentLocation;
    @Column(name="resume_path") private String resumePath;
    @Column(name="parsed_text", columnDefinition="LONGTEXT") private String parsedText;
    @Column(name = "stage")
    private String stage ="SOURCED";
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getTotalExp() {
		return totalExp;
	}
	public void setTotalExp(Integer totalExp) {
		this.totalExp = totalExp;
	}
	public String getSkillsJson() {
		return skillsJson;
	}
	public void setSkillsJson(String skillsJson) {
		this.skillsJson = skillsJson;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public String getParsedText() {
		return parsedText;
	}
	public void setParsedText(String parsedText) {
		this.parsedText = parsedText;
	}
	@Transient
    public List<String> getSkills(){ return JsonUtil.readList(skillsJson); }
    public void setSkills(List<String> skills){ this.skillsJson = JsonUtil.writeList(skills); }

    // getters/setters...
}
