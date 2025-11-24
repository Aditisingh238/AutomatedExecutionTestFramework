//1. CREATE TEST CASE
//
//API: POST /tests/integrate
//
// POSTMAN REQUEST
//URL:
//POST http://localhost:8080/tests/integrate
//
//Headers
//Content-Type: application/json
//
//        Body
//{
//        "testName": "LoginAPITest",
//        "testType": "API",
//        "framework": "REST-Assured",
//        "endpoint": "GET https://httpbin.org/status/200",
//        "method": "GET",
//        "description": "Check login API returns 200"
//        }
//
//         RESPONSE (200 OK)
//
//This response comes from:
//
//TestIntegrationService → testCaseRepository.save()
//
//{
//    "id": 1,
//        "testName": "LoginAPITest",
//        "testType": "API",
//        "framework": "REST-Assured",
//        "endpoint": "GET https://httpbin.org/status/200",
//        "method": "GET",
//        "description": "Check login API returns 200",
//        "createdAt": "2025-02-10T15:21:04.222"
//}
//
//DB INSERTED ROW — test_cases table
//+----+----------------+--------+----------------+------------------------------+--------+------------------------------+-------------------------+
//        | id | test_name      | type   | framework      | endpoint                     | method | description                  | created_at              |
//        +----+----------------+--------+----------------+------------------------------+--------+------------------------------+-------------------------+
//        | 1  | LoginAPITest   | API    | REST-Assured   | GET https://httpbin.org/...  | GET    | Check login API returns 200  | 2025-02-10 15:21:04    |
//        +----+----------------+--------+----------------+------------------------------+--------+------------------------------+-------------------------+
//
//         2. EXECUTE TEST CASE
//
//API: POST /tests/{id}/execute
//
// POSTMAN REQUEST
//URL:
//POST http://localhost:8080/tests/1/execute
//
//
//        (No request body needed.)
//
//         RESPONSE (200 OK)
//
//Execution results depend on REST-Assured output.
//
//        {
//        "id": 7,
//        "suiteName": "single-run",
//        "triggeredBy": "LoginAPITest",
//        "executionMode": "API",
//        "totalTests": 1,
//        "status": "PASSED",
//        "startedAt": "2025-02-10T16:32:01.200",
//        "completedAt": "2025-02-10T16:32:01.990"
//        }
//
//         DB ROW in test_executions table
//+----+-------------+--------------+--------------+-------------+--------+---------------------------+---------------------------+
//        | id | suite_name  | triggered_by | execution_mode| total_tests | status | started_at                | completed_at             |
//        +----+-------------+--------------+--------------+-------------+--------+---------------------------+---------------------------+
//        | 7  | single-run  | LoginAPITest | API          | 1           | PASSED | 2025-02-10 16:32:01.200   | 2025-02-10 16:32:01.990  |
//        +----+-------------+--------------+--------------+-------------+--------+---------------------------+---------------------------+
//
//         3. SCHEDULE TEST SUITE — PARALLEL EXECUTION
//
//API: POST /schedule/run
//
// POSTMAN REQUEST
//URL:
//POST http://localhost:8080/schedule/run
//
//Headers
//Content-Type: application/json
//
//        Body
//{
//        "suiteName": "RegressionSuite_V1",
//        "triggeredBy": "QAUser1",
//        "executionMode": "PARALLEL",
//        "tests": [1, 2, 3]
//        }
//
//         RESPONSE (200 OK)
//
//You get an array of executions (one per test).
//
//        [
//        {
//        "id": 11,
//        "suiteName": "RegressionSuite_V1",
//        "triggeredBy": "QAUser1",
//        "executionMode": "API",
//        "totalTests": 1,
//        "status": "PASSED"
//        },
//        {
//        "id": 12,
//        "suiteName": "RegressionSuite_V1",
//        "triggeredBy": "QAUser1",
//        "executionMode": "WEB",
//        "totalTests": 1,
//        "status": "FAILED"
//        },
//        {
//        "id": 13,
//        "suiteName": "RegressionSuite_V1",
//        "triggeredBy": "QAUser1",
//        "executionMode": "API",
//        "totalTests": 1,
//        "status": "PASSED"
//        }
//        ]
//
//         DB ROWS INSERTED in test_executions
//
//For tests 1,2,3 executed together:
//
//id | suiteName            | status
//-------------------------------------
//        11 | RegressionSuite_V1   | PASSED
//12 | RegressionSuite_V1   | FAILED
//13 | RegressionSuite_V1   | PASSED
//
//
//Multithreading is used → these run in parallel.
//
//        4. GENERATE REPORT
//
//API: POST /reports/generate
//
// POSTMAN REQUEST
//URL:
//POST http://localhost:8080/reports/generate
//
//Body
//[11, 12, 13]
//
//         RESPONSE (200 OK)
//
//Returns HTML report path:
//
//        "./reports/report-1739250255223.html"
//
//Also automatically generates:
//
//        ✔ HTML Report
//✔ CSV Report
//
//Inside /reports folder:
//
//report-1739250255223.html
//report-1739250255223.csv
//
//5. ANALYTICS — VIEW TRENDS
//
//API: GET /analytics/trends/{suiteId}
//
//         POSTMAN REQUEST
//URL:
//GET http://localhost:8080/analytics/trends/1
//
//         RESPONSE (200 OK)
//
//Based on test_results table:
//
//        {
//        "PASSED": 10,
//        "FAILED": 2,
//        "SKIPPED": 1
//        }
//
//         ALL ENDPOINT SUMMARY
//API	Method	Purpose
///tests/integrate	POST	Create test case
//        /tests/{id}	GET	Read test details
///tests/{id}/execute	POST	Run 1 test
///schedule/run	POST	Run suite in parallel
///reports/generate	POST	Generate HTML/CSV reports
///analytics/trends/{suiteId}	GET	View trends
///logs/collect	POST