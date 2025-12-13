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
@Table(name = "test_logs")
public class TestLog {
    // getters/setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long executionId;
    private Long testId;
    @Column(length = 4000)
    private String errorLog;
    private String screenshotPath;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime logCollectedAt;




}
