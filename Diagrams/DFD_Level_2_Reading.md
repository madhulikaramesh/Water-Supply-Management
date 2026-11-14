```mermaid
flowchart TB
    A[Municipal Administrator]

    B((3.1 Select Water Connection))
    C((3.2 Enter Meter Readings))
    D((3.3 Validate Readings))
    E((3.4 Calculate Water Consumption))
    F((3.5 Save Meter Reading))
    G((3.6 View Meter Reading))

    H[(Meter Reading Table)]

    A --> B
    B --> C
    C --> D
    D -->|Valid Readings| E
    D -->|Invalid Readings| A
    E --> F
    F --> H
    H --> G
    A --> G
    G --> A
