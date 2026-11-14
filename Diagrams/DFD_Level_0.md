```mermaid
flowchart LR
    A[Municipal Administrator]
    B((0. Water Supply Management System))
    C[(MySQL Database)]

    A -->|Consumer, Connection, Reading and Payment Details| B
    B -->|Bills, Reports and Status| A
    B <-->|Store and Retrieve Data| C
