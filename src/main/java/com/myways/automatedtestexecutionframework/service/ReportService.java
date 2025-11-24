
package com.myways.automatedtestexecutionframework.service;

import com.myways.automatedtestexecutionframework.entity.TestExecution;
import com.myways.automatedtestexecutionframework.repository.TestExecutionRepository;
import com.myways.automatedtestexecutionframework.util.ReportGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class ReportService {

    private final TestExecutionRepository repo;

    @Value("${app.reports.path:./reports}")
    private String reportsPath;

    public ReportService(TestExecutionRepository repo) {
        this.repo = repo;
    }

    // -------- Generate HTML report --------
    public Path generateHtmlReport() throws Exception {
        List<TestExecution> list = repo.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("No test executions found to generate report.");
        }
        return ReportGenerator.generateHtmlReport(list, Path.of(reportsPath));
    }

    // -------- Generate CSV report --------
    public Path generateCsvReport() throws Exception {
        List<TestExecution> list = repo.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("No test executions found to generate report.");
        }
        return ReportGenerator.generateCsvReport(list, Path.of(reportsPath));
    }
}
