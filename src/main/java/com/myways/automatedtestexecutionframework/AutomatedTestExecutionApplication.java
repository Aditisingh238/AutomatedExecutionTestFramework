package com.myways.automatedtestexecutionframework;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.myways.automatedtestexecutionframework.entity")
@EnableJpaRepositories("com.myways.automatedtestexecutionframework.repository")
public class AutomatedTestExecutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutomatedTestExecutionApplication.class, args);
    }
}
