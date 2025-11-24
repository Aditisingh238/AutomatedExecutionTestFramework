package com.myways.automatedtestexecutionframework.controller;
import com.myways.automatedtestexecutionframework.dto.TestCaseDto;
import com.myways.automatedtestexecutionframework.entity.TestCase;
import com.myways.automatedtestexecutionframework.entity.TestExecution;
import com.myways.automatedtestexecutionframework.repository.TestCaseRepository;
import com.myways.automatedtestexecutionframework.repository.TestExecutionRepository;
import com.myways.automatedtestexecutionframework.service.TestIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestIntegrationService service;

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestExecutionRepository executionRepository;

    @PostMapping("/integrate")
    public ResponseEntity<TestCase> integrate(@RequestBody TestCaseDto dto) {
        TestCase tc = new TestCase();
        tc.setTestName(dto.testName);
        tc.setTestType(dto.testType);
        tc.setFramework(dto.framework);
        tc.setEndpoint(dto.endpoint);
        tc.setMethod(dto.method);
        tc.setDescription(dto.description);
        TestCase saved = service.integrate(tc);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTest(@PathVariable Long id) {
        Optional<TestCase> tc = service.getTestCase(id);
        return tc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TestCase> getAll() {
        return testCaseRepository.findAll();
    }

    @PostMapping("/{id}/execute")
    public ResponseEntity<TestExecution> execute(@PathVariable Long id) throws Exception {
        TestExecution exec = service.executeTest(id);
        return ResponseEntity.ok(exec);
    }

    @GetMapping("/executions/{id}")
    public ResponseEntity<?> getExecution(@PathVariable Long id) {
        return executionRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
