package com.myways.automatedtestexecutionframework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test_executions")
public class TestExecution {
    // getters/setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String suiteName;
    private String triggeredBy;
    private String executionMode;
    private Integer totalTests;
    private String status; // PENDING,RUNNING,COMPLETED,FAILED
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startedAt;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime completedAt;

    @Column(length = 2000)
    private String errorMessage;

    private String screenshotPath;

}
