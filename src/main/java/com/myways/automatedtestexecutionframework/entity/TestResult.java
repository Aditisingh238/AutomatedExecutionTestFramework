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

    @Column(columnDefinition = "TIMESTAMP")


    private LocalDateTime executedAt;

}
