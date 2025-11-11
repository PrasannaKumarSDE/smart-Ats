# Smart ATS (Applicant Tracking System)

Smart ATS is a lightweight end-to-end recruitment workflow tool built with:

- **Backend:** Java + Spring Boot + MySQL  
- **Frontend:** Single Page Application (HTML + CSS + JavaScript)  
- **Architecture:** REST APIs, Minimal SPA, No frameworks required  

This system covers the complete hiring lifecycle:

1. Create Job Openings  
2. Add Candidates  
3. Apply Candidates to Jobs  
4. Automatic Skill Matching Score  
5. Visual Pipeline (APPLIED → SCREENING → INTERVIEW → OFFER → ONBOARDING → REJECTED)  
6. Stage Movement for Final Selection  

---

## Features

### Job Management
- Add, edit, delete job postings
- Store required experience and skill requirements

### Candidate Management
- Add candidate profiles with experience and skills
- No external resume parsing dependency

### Application Pipeline
- Apply candidates to job openings
- Auto-score based on skill match
- Pipeline board displays real-time progress

### Search & Shortlist
- Quick text-based candidate search
- Auto-suggestion based on match score

---

## Sample Skill Matching Logic
Scores are calculated based on overlap of required job skills and candidate skills.

≥ 70% Match → Candidate moves to **SCREENING** automatically.

---

## API Endpoints

| Feature | Method | Endpoint |
|--------|--------|----------|
| Create Job | POST | /api/jobs |
| List Jobs | GET | /api/jobs |
| Create Candidate | POST | /api/candidates |
| List Candidates | GET | /api/candidates |
| Apply Candidate | POST | /api/applications |
| Move Stage | POST | /api/applications/{id}/move |
| Pipeline by Job | GET | /api/applications/job/{jobId} |
| Search Candidates | GET | /api/search/candidates?q=keyword |

---

## Database Tables

- `jobs`  
- `candidates`  
- `applications`  

---

## How to Run

### 1. Configure DB
Update `application.properties`:
spring.datasource.url=jdbc:mysql://localhost:3306/ats
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


### 3. Open Frontend
Open `index.html` in your browser.

---

## Screens

- Job Management Dashboard  
- Candidate List  
- Application Pipeline  
- Search & Auto Shortlist  



