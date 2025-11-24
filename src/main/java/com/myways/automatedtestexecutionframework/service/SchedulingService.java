package com.myways.automatedtestexecutionframework.service;
import com.myways.automatedtestexecutionframework.dto.ScheduleRequest;
import com.myways.automatedtestexecutionframework.entity.TestExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    private ExecutionService executionService;

    public List<TestExecution> scheduleRun(ScheduleRequest req) throws InterruptedException {
        if (req == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }

        List<Long> ids = req.getTestCaseIds();
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("testCaseIds (or tests) must be provided and cannot be empty");
        }

        return executionService.runSuiteParallel(ids);
    }
}
