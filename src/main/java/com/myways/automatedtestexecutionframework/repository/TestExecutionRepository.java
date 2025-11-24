package com.myways.automatedtestexecutionframework.repository;
import com.myways.automatedtestexecutionframework.entity.TestExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {
    List<TestExecution> findBySuiteName(String suiteName);
}
