package com.myways.automatedtestexecutionframework.controller;

import com.myways.automatedtestexecutionframework.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;
//analyticsService
    @GetMapping("/trends/{suiteId}")
    public ResponseEntity<?> trends(@PathVariable("suiteId") Long suiteId) {
        Map<String, Long> map = analyticsService.getStatusCounts(suiteId);
        return ResponseEntity.ok(map);
    }
}
