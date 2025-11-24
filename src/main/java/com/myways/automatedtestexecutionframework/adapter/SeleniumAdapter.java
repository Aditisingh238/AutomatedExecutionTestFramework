package com.myways.automatedtestexecutionframework.adapter;

import com.myways.automatedtestexecutionframework.entity.TestExecution;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class SeleniumAdapter {
    public static void runUiTest(String payload, TestExecution execution, Path reportsDir) {
        execution.setStartedAt(LocalDateTime.now());
        try {
            // Placeholder: implement WebDriver flows here (e.g., WebDriverManager + Chrome headless)
            Thread.sleep(500); // simulate
            execution.setStatus("PASSED");
        } catch (Exception ex) {
            execution.setStatus("FAILED");
            execution.setErrorMessage(ex.getMessage());
            execution.setScreenshotPath(reportsDir.resolve("screenshot-" + System.currentTimeMillis() + ".png").toString());
        } finally {
            execution.setCompletedAt(LocalDateTime.now());
        }
    }
}
