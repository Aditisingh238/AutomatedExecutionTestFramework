package com.myways.automatedtestexecutionframework.controller;

import com.myways.automatedtestexecutionframework.dto.ScheduleRequest;
import com.myways.automatedtestexecutionframework.entity.TestExecution;
import com.myways.automatedtestexecutionframework.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private SchedulingService schedulingService;

    @PostMapping("/run")
    public ResponseEntity<?> runSchedule(@RequestBody(required = false) ScheduleRequest req) {
        try {
            List<TestExecution> results = schedulingService.scheduleRun(req);
            return ResponseEntity.ok(results);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(
                    java.util.Map.of("error", ex.getMessage())
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(500).body(java.util.Map.of("error", "Execution interrupted"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(java.util.Map.of("error", "Internal server error", "details", e.getMessage()));
        }
    }
}
