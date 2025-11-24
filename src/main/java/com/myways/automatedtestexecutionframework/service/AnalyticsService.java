package com.myways.automatedtestexecutionframework.service;
import com.myways.automatedtestexecutionframework.entity.TestResult;
import com.myways.automatedtestexecutionframework.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private TestResultRepository resultRepository;

    public Map<String, Long> getStatusCounts(Long suiteId) {
        List<TestResult> list = resultRepository.findBySuiteId(suiteId);
        return list.stream().collect(Collectors.groupingBy(TestResult::getResult, Collectors.counting()));
    }
}
