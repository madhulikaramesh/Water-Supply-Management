```mermaid
flowchart TB
    A[Municipal Administrator]

    subgraph FRONTEND["Frontend Layer"]
        B[React Application]
        C[Dashboard]
        D[Consumer Management]
        E[Water Connection Management]
        F[Meter Reading Management]
        G[Water Bill Management]
        H[Payment Management]
    end

    subgraph BACKEND["Backend Layer"]
        I[Spring Boot REST API]
        J[Controller Layer]
        K[Service Layer]
        L[Repository Layer]
        M[JDBC / Database Connectivity]
    end

    subgraph DATABASE["Database Layer"]
        N[(MySQL Database)]
    end

    subgraph ANALYSIS["Python Data Analysis Layer"]
        O[Python Application]
        P[Pandas Data Analysis]
        Q[Matplotlib Visualization]
        R[Consumption Charts and Reports]
    end

    A --> B
    B --> C
    B --> D
    B --> E
    B --> F
    B --> G
    B --> H
    B <--> I
    I --> J
    J --> K
    K --> L
    L --> M
    M <--> N
    N --> O
    O --> P
    P --> Q
    Q --> R
