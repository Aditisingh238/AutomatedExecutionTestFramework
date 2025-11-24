package com.myways.automatedtestexecutionframework.repository;
import com.myways.automatedtestexecutionframework.entity.TestLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TestLogRepository extends JpaRepository<TestLog, Long> {}
