package com.myways.automatedtestexecutionframework.service;

import com.myways.automatedtestexecutionframework.adapter.ApiAdapter;
import com.myways.automatedtestexecutionframework.adapter.SeleniumAdapter;
import com.myways.automatedtestexecutionframework.entity.TestCase;
import com.myways.automatedtestexecutionframework.entity.TestExecution;
import com.myways.automatedtestexecutionframework.repository.TestCaseRepository;
import com.myways.automatedtestexecutionframework.repository.TestExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TestIntegrationService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestExecutionRepository executionRepository;

    @Value("${app.reports.path:./reports}")
    private String reportsPath;

    public TestCase integrate(TestCase tc) {
        return testCaseRepository.save(tc);
    }

    public Optional<TestCase> getTestCase(Long id) { return testCaseRepository.findById(id); }

    public TestExecution executeTest(Long testCaseId) throws Exception {
        TestCase tc = testCaseRepository.findById(testCaseId).orElseThrow(() -> new RuntimeException("TestCase not found"));
        TestExecution exec = new TestExecution();
        exec.setSuiteName("single-run");
        exec.setTriggeredBy(tc.getTestName());
        exec.setExecutionMode(tc.getTestType());
        exec.setTotalTests(1);
        exec.setStatus("PENDING");
        exec.setStartedAt(LocalDateTime.now());
        executionRepository.save(exec);

        Path reportsDir = Path.of(reportsPath);
        if (!Files.exists(reportsDir)) Files.createDirectories(reportsDir);

        if ("WEB".equalsIgnoreCase(tc.getTestType())) {
            SeleniumAdapter.runUiTest(tc.getEndpoint(), exec, reportsDir);
        } else {
            ApiAdapter.runApiTest(tc.getEndpoint(), exec, reportsDir);
        }

        exec.setCompletedAt(LocalDateTime.now());
        return executionRepository.save(exec);
    }
}
