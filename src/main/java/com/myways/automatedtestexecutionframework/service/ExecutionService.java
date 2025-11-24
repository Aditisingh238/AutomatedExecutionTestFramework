package com.myways.automatedtestexecutionframework.service;
import com.myways.automatedtestexecutionframework.entity.TestExecution;
import com.myways.automatedtestexecutionframework.repository.TestExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ExecutionService {

    @Autowired
    private TestIntegrationService integrationService;

    @Autowired
    private TestExecutionRepository executionRepository;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private Executor testExecutor;

//    public List<TestExecution> runSuiteParallel(List<Long> testCaseIds) throws InterruptedException {
//        ExecutorService execService;
//        if (testExecutor instanceof ExecutorService) execService = (ExecutorService) testExecutor;
//        else execService = Executors.newFixedThreadPool(4);
//        if (testCaseIds == null || testCaseIds.isEmpty()) {
//            throw new IllegalArgumentException("Test Case IDs cannot be null or empty");
//        }
//        List<Future<TestExecution>> futures = new ArrayList<>();
//        for (Long id: testCaseIds) {
//            futures.add(((ExecutorService)execService).submit(() -> integrationService.executeTest(id)));
//        }
//
//        List<TestExecution> results = new ArrayList<>();
//        for (Future<TestExecution> f: futures) {
//            try {
//                results.add(f.get());
//            } catch (ExecutionException e) {
//                TestExecution te = new TestExecution();
//                te.setStatus("FAILED");
//                te.setTriggeredBy("system");
//                execRepositorySaveFallback(te);
//                results.add(te);
//            }
//        }
//
//        return results;
//    }
public List<TestExecution> runSuiteParallel(List<Long> testCaseIds) throws InterruptedException {

    System.out.println(" Incoming testCaseIds: " + testCaseIds);

    if (testCaseIds == null || testCaseIds.isEmpty()) {
        throw new IllegalArgumentException("Test Case IDs cannot be null or empty");
    }

    ExecutorService execService =
            (testExecutor instanceof ExecutorService)
                    ? (ExecutorService) testExecutor
                    : Executors.newFixedThreadPool(4);

    List<Future<TestExecution>> futures = new ArrayList<>();

    for (Long id : testCaseIds) {
        futures.add(execService.submit(() -> integrationService.executeTest(id)));
    }

    List<TestExecution> results = new ArrayList<>();
    for (Future<TestExecution> f : futures) {
        try {
            results.add(f.get());
        } catch (ExecutionException e) {
            TestExecution te = new TestExecution();
            te.setStatus("FAILED");
            te.setTriggeredBy("system");
            execRepositorySaveFallback(te);
            results.add(te);
        }
    }
    return results;
}

    private void execRepositorySaveFallback(TestExecution te) {
        try { executionRepository.save(te); } catch (Exception ignored) {}
    }
}
