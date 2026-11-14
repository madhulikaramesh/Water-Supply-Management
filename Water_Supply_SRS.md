# SOFTWARE REQUIREMENTS SPECIFICATION (SRS)

## Project Title

Water Supply Management and Consumption Analysis System

## 1. Introduction

### 1.1 Purpose

The purpose of this project is to develop a Water Supply Management and Consumption Analysis System to manage consumer information, water connections, meter readings, water bills, and payment records. The system uses a web-based frontend, Java Spring Boot backend, MySQL database, and Python for data analysis and visualization.

### 1.2 Scope

The system provides a centralized platform for managing water supply records. It helps the municipal administrator maintain consumer details, monitor water connections, record meter readings, manage bills and payments, and analyze water-related data.

### 1.3 Objectives

- Maintain consumer information.
- Manage water connections.
- Record previous and current meter readings.
- Maintain water consumption records.
- Manage water bills.
- Maintain payment details.
- Store and retrieve records from the database.
- Analyze available water supply data.
- Generate charts for understanding consumption and payment information.

## 2. Overall Description

### 2.1 Product Perspective

The system follows a frontend-backend-database architecture.

- React provides the user interface.
- Java Spring Boot handles backend operations and REST API requests.
- MySQL stores application data.
- Python is used for database analysis and visualization.

### 2.2 User

The primary user of the system is the Municipal Administrator, who manages consumer details, connections, meter readings, bills, and payments.

### 2.3 Technologies Used

- Frontend: React
- Backend: Java Spring Boot
- Programming Language: Java
- Database: MySQL
- Data Analysis: Python
- Python Libraries: Pandas, Matplotlib, MySQL Connector
- Development Environment: Visual Studio Code

## 3. Functional Requirements

1. Consumer Management: Add, view, and delete consumer details.
2. Water Connection Management: Maintain water connection information, including connection ID, consumer ID, connection type, and status.
3. Meter Reading Management: Record and maintain previous and current meter readings with reading dates.
4. Water Consumption Calculation: Calculate water consumption using current and previous meter readings.
5. Water Bill Management: Maintain water bill details, including units consumed, bill amount, and payment status.
6. Payment Management: Record, view, update, and delete payment information.
7. Database Management: Store, retrieve, and maintain records in the MySQL database.
8. Data Analysis: Retrieve available records from the database and analyze them using Python.
9. Data Visualization: Generate charts for consumer information, payments, bill status, and water consumption.

## 4. Non-Functional Requirements

1. Performance: The system should process user requests and retrieve records efficiently.
2. Reliability: The system should maintain accurate records and calculations.
3. Security: Database credentials and application access should be protected.
4. Usability: The application should provide a simple and user-friendly interface.
5. Maintainability: The application should be easy to modify and extend.
6. Data Integrity: Relationships between database tables should be maintained correctly.
7. Compatibility: The application should run in a supported web browser and development environment.

## 5. Hardware Requirements

- Processor: Intel i3 or above
- RAM: 4 GB minimum
- Hard Disk: 500 GB or above
- Operating System: Windows 10 or above

## 6. Software Requirements

- Visual Studio Code
- Java Development Kit (JDK)
- Spring Boot
- Node.js and npm
- MySQL Server
- MySQL Workbench
- Python 3.13
- Pandas
- Matplotlib
- MySQL Connector for Python
- Web Browser

## 7. Input Requirements

- Consumer ID
- Consumer Name
- Address
- Contact Number
- Connection ID
- Consumer ID associated with the connection
- Connection Type
- Connection Date
- Connection Status
- Meter Reading ID
- Previous Meter Reading
- Current Meter Reading
- Reading Date
- Bill ID
- Units Consumed
- Bill Amount
- Due Date
- Payment Status
- Payment ID
- Payment Date
- Amount Paid
- Payment Method

## 8. Output Requirements

- Consumer Details
- Water Connection Details
- Meter Reading Details
- Water Consumption Information
- Water Bill Details
- Payment Records
- Bill Payment Status
- Consumer Chart
- Payment Chart
- Bill Status Chart
- Water Consumption Chart

## 9. Constraints

- A valid consumer record must exist before creating an associated water connection.
- A valid water connection must exist before recording its meter reading.
- Meter readings must be entered correctly.
- A valid bill record is required for its associated payment.
- Database connectivity is required to store and retrieve application records.
- Python analysis requires the necessary libraries and database connection.

## 10. Conclusion

The Water Supply Management and Consumption Analysis System provides a centralized solution for managing consumer information, water connections, meter readings, bills, and payments. The integration of React, Java Spring Boot, MySQL, and Python supports application management and data visualization. The system helps organize water supply records and provides useful information for administrative monitoring.
