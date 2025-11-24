package com.myways.automatedtestexecutionframework.controller;

import com.myways.automatedtestexecutionframework.entity.TestLog;
import com.myways.automatedtestexecutionframework.repository.TestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    private TestLogRepository logRepository;

    @PostMapping("/collect")
    public ResponseEntity<?> collect(@RequestBody TestLog log) {
        TestLog saved = logRepository.save(log);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<TestLog> getAll() {
        return logRepository.findAll();
    }
}
