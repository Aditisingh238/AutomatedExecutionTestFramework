package com.myways.automatedtestexecutionframework.repository;
import com.myways.automatedtestexecutionframework.entity.TestExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {
    List<TestExecution> findBySuiteName(String suiteName);
}
