
package com.myways.automatedtestexecutionframework.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleRequest {
    private String suiteName;
    private String triggeredBy;
    private String executionMode; // PARALLEL or SEQUENTIAL

    // accept both "tests" and "testCaseIds" from JSON
    @JsonAlias({"tests", "testCaseIds"})
    private List<Long> testCaseIds = new ArrayList<>();

    public ScheduleRequest() {}

    public String getSuiteName() { return suiteName; }
    public void setSuiteName(String suiteName) { this.suiteName = suiteName; }

    public String getTriggeredBy() { return triggeredBy; }
    public void setTriggeredBy(String triggeredBy) { this.triggeredBy = triggeredBy; }

    public String getExecutionMode() { return executionMode; }
    public void setExecutionMode(String executionMode) { this.executionMode = executionMode; }

    public List<Long> getTestCaseIds() { return testCaseIds; }
    public void setTestCaseIds(List<Long> testCaseIds) {
        this.testCaseIds = testCaseIds == null ? new ArrayList<>() : testCaseIds;
    }
}
