package com.myways.automatedtestexecutionframework.adapter;

import com.myways.automatedtestexecutionframework.entity.TestExecution;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class ApiAdapter {
    public static void runApiTest(String payload, TestExecution execution, Path reportsDir) {
        execution.setStartedAt(LocalDateTime.now());
        try {
            String[] parts = payload.split("\\s+", 2);
            String method = parts[0].toUpperCase();
            String url = parts.length > 1 ? parts[1] : parts[0];

            Response response;
            if ("GET".equals(method)) {
                response = RestAssured.get(url);
            } else if ("POST".equals(method)) {
                response = RestAssured.given().body("").post(url);
            } else {
                response = RestAssured.get(url);
            }

            int code = response.getStatusCode();
            if (code >= 200 && code < 300) execution.setStatus("PASSED");
            else {
                execution.setStatus("FAILED");
                execution.setErrorMessage("Status code: " + code);
            }
        } catch (Exception ex) {
            execution.setStatus("FAILED");
            execution.setErrorMessage(ex.getMessage());
        } finally {
            execution.setCompletedAt(LocalDateTime.now());
        }
    }
}
