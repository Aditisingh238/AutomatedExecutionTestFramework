package com.myways.automatedtestexecutionframework.adapter;
//
//import com.myways.automatedtestexecutionframework.entity.TestExecution;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

import com.myways.automatedtestexecutionframework.entity.TestExecution;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class SeleniumAdapter {
//    public static void runUiTest(String payload, TestExecution execution, Path reportsDir) {
//        execution.setStartedAt(LocalDateTime.now());
//        try {
//            // Placeholder: implement WebDriver flows here (e.g., WebDriverManager + Chrome headless)
//            Thread.sleep(500); // simulate
//            execution.setStatus("PASSED");
//        } catch (Exception ex) {
//            execution.setStatus("FAILED");
//            execution.setErrorMessage(ex.getMessage());
//            execution.setScreenshotPath(reportsDir.resolve("screenshot-" + System.currentTimeMillis() + ".png").toString());
//        } finally {
//            execution.setCompletedAt(LocalDateTime.now());
//        }
//    }



    public static void runUiTest(String payload, TestExecution execution, Path reportsDir) {
        execution.setStartedAt(LocalDateTime.now());
        WebDriver driver = null;

        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(payload);

            if (driver.getTitle() == null || driver.getTitle().isBlank()) {
                throw new RuntimeException("Page title is empty");
            }

            execution.setStatus("PASSED");

        } catch (Exception ex) {
            execution.setStatus("FAILED");
            execution.setErrorMessage(ex.getMessage());
            execution.setScreenshotPath(
                    reportsDir.resolve("web-failure-" + System.currentTimeMillis() + ".png").toString()
            );
        } finally {
            if (driver != null) driver.quit();
            execution.setCompletedAt(LocalDateTime.now());
        }
    }

}
