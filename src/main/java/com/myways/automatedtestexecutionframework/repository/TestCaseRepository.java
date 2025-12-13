package com.myways.automatedtestexecutionframework.repository;

import com.myways.automatedtestexecutionframework.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {}
