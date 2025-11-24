package com.myways.automatedtestexecutionframework.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_logs")
public class TestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long executionId;
    private Long testId;
    @Column(length = 4000)
    private String errorLog;
    private String screenshotPath;
    private LocalDateTime logCollectedAt = LocalDateTime.now();

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getExecutionId() { return executionId; }
    public void setExecutionId(Long executionId) { this.executionId = executionId; }
    public Long getTestId() { return testId; }
    public void setTestId(Long testId) { this.testId = testId; }
    public String getErrorLog() { return errorLog; }
    public void setErrorLog(String errorLog) { this.errorLog = errorLog; }
    public String getScreenshotPath() { return screenshotPath; }
    public void setScreenshotPath(String screenshotPath) { this.screenshotPath = screenshotPath; }
    public LocalDateTime getLogCollectedAt() { return logCollectedAt; }
    public void setLogCollectedAt(LocalDateTime logCollectedAt) { this.logCollectedAt = logCollectedAt; }
}
