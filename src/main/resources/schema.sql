CREATE TABLE IF NOT EXISTS jobs (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  location VARCHAR(120),
  min_exp INT,
  max_exp INT,
  skills_json TEXT,
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS candidates (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  email VARCHAR(200) UNIQUE,
  phone VARCHAR(40),
  total_exp INT,
  skills_json TEXT,
  current_location VARCHAR(120),
  resume_path VARCHAR(300),
  parsed_text LONGTEXT
);

CREATE TABLE IF NOT EXISTS applications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  job_id BIGINT NOT NULL,
  candidate_id BIGINT NOT NULL,
  stage VARCHAR(40) NOT NULL,
  note TEXT,
  score DOUBLE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_app_job FOREIGN KEY (job_id) REFERENCES jobs(id),
  CONSTRAINT fk_app_cand FOREIGN KEY (candidate_id) REFERENCES candidates(id),
  UNIQUE KEY uq_job_cand (job_id, candidate_id)
);
