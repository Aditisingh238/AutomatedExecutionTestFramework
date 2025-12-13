
package com.myways.automatedtestexecutionframework.controller;

import com.myways.automatedtestexecutionframework.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/reports")
public class ReportController {



    //*****************1
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // -----------------------------------------------------
    // Generate HTML Report
    // -----------------------------------------------------
    @GetMapping("/generate/html")
    public ResponseEntity<?> generateHtml() {
        try {
            Path file = reportService.generateHtmlReport();
            return ResponseEntity.ok(
                    "HTML Report Generated Successfully\nFile: " + file.toAbsolutePath()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // -----------------------------------------------------
    // Generate CSV Report
    // -----------------------------------------------------
    @GetMapping("/generate/csv")
    public ResponseEntity<?> generateCsv() {
        try {
            Path file = reportService.generateCsvReport();
            return ResponseEntity.ok(
                    "CSV Report Generated Successfully\nFile: " + file.toAbsolutePath()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
