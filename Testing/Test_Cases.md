# TEST CASES
## Project Title
Water Supply Management and Consumption Analysis System

## 1. Introduction
This document describes the test cases used to verify the main functions of the Water Supply Management and Consumption Analysis System.

## 2. Testing Objectives
- Verify that consumer details can be managed correctly.
- Verify water connection records and their association with consumers.
- Verify meter readings and water consumption calculations.
- Verify water bill and payment records.
- Verify database connectivity and data storage.
- Verify Python-based data analysis and chart generation.

## 3. Test Environment
- Frontend: React
- Backend: Java Spring Boot
- Database: MySQL
- Data Analysis: Python
- Browser: Supported web browser
- Testing approach: Manual functional testing

## 4. Test Cases

| Test Case ID | Module | Test Scenario | Test Steps / Input | Expected Result |
|---|---|---|---|---|
| TC-01 | Consumer Management | Add a consumer with valid details | Enter valid consumer name, address, and contact number; submit the form | Consumer record should be saved and displayed |
| TC-02 | Consumer Management | View consumer records | Open the consumer list | Saved consumer records should be displayed |
| TC-03 | Consumer Management | Delete an existing consumer | Select an existing consumer and choose Delete | Consumer record should be deleted, subject to relationship constraints |
| TC-04 | Water Connection Management | Add a connection for an existing consumer | Select a valid consumer; enter connection type, date, and status; submit | Water connection should be saved with the selected consumer association |
| TC-05 | Water Connection Management | Prevent connection for an invalid consumer | Enter or select a consumer ID that does not exist | System should reject the invalid association and show an appropriate message |
| TC-06 | Meter Reading Management | Record a valid meter reading | Select a valid connection; enter previous reading, current reading, and reading date | Meter reading should be saved |
| TC-07 | Meter Reading Management | Validate meter reading values | Enter a current reading lower than the previous reading | System should reject the invalid reading or show a validation message |
| TC-08 | Water Consumption Calculation | Calculate water consumption | Enter valid previous and current readings | Consumption should equal current reading minus previous reading |
| TC-09 | Water Bill Management | Save a bill with valid details | Enter connection or consumer reference, units consumed, bill amount, due date, and payment status | Bill record should be saved |
| TC-10 | Water Bill Management | View bill records | Open the bill list | Existing bill records should be displayed |
| TC-11 | Payment Management | Record a valid payment | Select a valid bill; enter payment date, amount paid, and payment method | Payment record should be saved |
| TC-12 | Payment Management | Update a payment record | Open an existing payment, modify an editable field, and save | Updated payment details should be stored and displayed |
| TC-13 | Payment Management | Delete a payment record | Select an existing payment and choose Delete | Payment record should be deleted after confirmation, if required |
| TC-14 | Database Management | Verify database connectivity | Start the application with the configured MySQL database available | Application should connect to MySQL successfully |
| TC-15 | Database Management | Verify persistence of records | Add a record, restart or refresh the application, and view the record again | Saved record should remain available |
| TC-16 | Data Analysis | Retrieve records for analysis | Run the Python analysis program with valid database configuration | Program should retrieve the available records |
| TC-17 | Data Visualization | Generate consumer and payment charts | Run the relevant Python visualization program | The requested charts should be generated using available data |
| TC-18 | Data Visualization | Handle empty or unavailable data | Run analysis when the relevant table has no records | Program should handle the empty data condition without an unhandled error |

## 5. Test Result Record

For each test case, record the actual result and mark the status as Pass or Fail after executing it.

| Test Case ID | Actual Result | Status (Pass/Fail) | Remarks |
|---|---|---|---|
| TC-01 | To be filled during testing | Not Run | |
| TC-02 | To be filled during testing | Not Run | |
| TC-03 | To be filled during testing | Not Run | |
| TC-04 | To be filled during testing | Not Run | |
| TC-05 | To be filled during testing | Not Run | |
| TC-06 | To be filled during testing | Not Run | |
| TC-07 | To be filled during testing | Not Run | |
| TC-08 | To be filled during testing | Not Run | |
| TC-09 | To be filled during testing | Not Run | |
| TC-10 | To be filled during testing | Not Run | |
| TC-11 | To be filled during testing | Not Run | |
| TC-12 | To be filled during testing | Not Run | |
| TC-13 | To be filled during testing | Not Run | |
| TC-14 | To be filled during testing | Not Run | |
| TC-15 | To be filled during testing | Not Run | |
| TC-16 | To be filled during testing | Not Run | |
| TC-17 | To be filled during testing | Not Run | |
| TC-18 | To be filled during testing | Not Run | |

## 6. Conclusion
These test cases provide a structured way to check the major modules of the Water Supply Management and Consumption Analysis System. The actual result and Pass/Fail status should be updated only after each test case has been executed.
