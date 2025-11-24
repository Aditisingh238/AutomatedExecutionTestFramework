
package com.myways.automatedtestexecutionframework.util;

import com.myways.automatedtestexecutionframework.entity.TestExecution;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportGenerator {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // -------------------------------------------------------
    // 1) Generate HTML REPORT
    // -------------------------------------------------------
    public static Path generateHtmlReport(List<TestExecution> executions, Path outputDir) throws IOException {

        if (!Files.exists(outputDir)) Files.createDirectories(outputDir);

        Path file = outputDir.resolve("report-" + System.currentTimeMillis() + ".html");

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE)) {

            writer.write("""
                    <html>
                    <head>
                        <title>Test Report</title>
                        <style>
                            body { font-family: Arial, sans-serif; }
                            table { border-collapse: collapse; width: 100%; }
                            th, td { border: 1px solid #444; padding: 8px; text-align: left; }
                            th { background-color: #f2f2f2; }
                            tr:nth-child(even) { background-color: #f9f9f9; }
                        </style>
                    </head>
                    <body>
                        <h1>Test Run Report</h1>
                        <table>
                            <tr>
                                <th>ID</th>
                            <th>Suite Name</th>
                                <th>Status</th>
                                <th>Started At</th>
                                <th>Completed At</th>
                                <th>Error Message</th>
                            </tr>
                    """);

            for (TestExecution e : executions) {
                writer.write("<tr>");
                writer.write("<td>" + safe(e.getId()) + "</td>");
                writer.write("<td>" + safe(e.getSuiteName()) + "</td>");
                writer.write("<td>" + safe(e.getStatus()) + "</td>");
                writer.write("<td>" + (e.getStartedAt() == null ? "" : fmt.format(e.getStartedAt())) + "</td>");
                writer.write("<td>" + (e.getCompletedAt() == null ? "" : fmt.format(e.getCompletedAt())) + "</td>");
                writer.write("<td>" + safe(e.getErrorMessage()) + "</td>");
                writer.write("</tr>");
            }

            writer.write("""
                        </table>
                    </body>
                    </html>
                    """);
        }

        return file;
    }


    // -------------------------------------------------------
    // 2) Generate CSV REPORT
    // -------------------------------------------------------
    public static Path generateCsvReport(List<TestExecution> executions, Path outputDir) throws IOException {

        if (!Files.exists(outputDir)) Files.createDirectories(outputDir);

        Path file = outputDir.resolve("report-" + System.currentTimeMillis() + ".csv");

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE)) {

            // Header EXACTLY as you required
            writer.write("id,suiteName,status,startedAt,completedAt,errorMessage\n");

            for (TestExecution e : executions) {
                writer.write(String.format(
                        "%s,%s,%s,%s,%s,%s\n",
                        safe(e.getId()),
                        safe(e.getSuiteName()),
                        safe(e.getStatus()),
                        (e.getStartedAt() == null ? "" : fmt.format(e.getStartedAt())),
                        (e.getCompletedAt() == null ? "" : fmt.format(e.getCompletedAt())),
                        safe(e.getErrorMessage())
                ));
            }
        }

        return file;
    }


    // -------------------------------------------------------
    // Utility to sanitize CSV values
    // -------------------------------------------------------
    private static String safe(Object o) {
        return o == null ? "" : o.toString().replace(",", ";");
    }

}
