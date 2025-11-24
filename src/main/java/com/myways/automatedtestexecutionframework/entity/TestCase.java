package com.myways.automatedtestexecutionframework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "test_cases")
public class TestCase {
    // getters / setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testName;
    private String testType;
    private String framework;
    @Column(length = 1000)
    private String endpoint;
    private String method;
    @Column(length = 2000)
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();

}
