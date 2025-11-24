package com.myways.automatedtestexecutionframework.repository;
import com.myways.automatedtestexecutionframework.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findBySuiteId(Long suiteId);
}
