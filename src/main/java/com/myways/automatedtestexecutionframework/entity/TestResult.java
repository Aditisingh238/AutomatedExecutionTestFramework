package com.myways.automatedtestexecutionframework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "test_results")
public class TestResult {
    // getters/setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long suiteId;
    private Long testId;
    private Long executionId;
    private String result;
    private Integer durationSeconds;
    private LocalDateTime executedAt = LocalDateTime.now();

}
