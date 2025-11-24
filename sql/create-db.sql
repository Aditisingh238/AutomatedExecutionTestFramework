CREATE DATABASE IF NOT EXISTS automated_regression_framework CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE automated_regression_framework;

CREATE TABLE IF NOT EXISTS test_cases (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  test_name VARCHAR(255),
  test_type VARCHAR(50),
  framework VARCHAR(100),
  endpoint VARCHAR(1000),
  method VARCHAR(10),
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS test_executions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  suite_name VARCHAR(255),
  triggered_by VARCHAR(255),
  execution_mode VARCHAR(50),
  total_tests INT,
  status VARCHAR(50),
  started_at TIMESTAMP NULL,
  completed_at TIMESTAMP NULL,
  error_message TEXT,
  screenshot_path VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS test_logs (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  execution_id BIGINT,
  test_id BIGINT,
  error_log TEXT,
  screenshot_path VARCHAR(1000),
  log_collected_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS test_results (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  suite_id BIGINT,
  test_id BIGINT,
  execution_id BIGINT,
  result VARCHAR(50),
  duration_seconds INT,
  executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
