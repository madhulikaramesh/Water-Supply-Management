```mermaid
flowchart TB
    A[Municipal Administrator]

    B((5.1 Select Water Bill))
    C((5.2 Enter Payment Details))
    D((5.3 Validate Payment))
    E((5.4 Save Payment Details))
    F((5.5 Update Payment))
    G((5.6 View Payment Details))
    H((5.7 Delete Payment))

    I[(Payment Table)]
    J[(Water Bill Table)]

    A --> B
    B <--> J
    B --> C
    C --> D
    D -->|Valid Payment| E
    D -->|Invalid Payment| A
    E --> I
    A --> F
    F --> I
    A --> G
    I --> G
    G --> A
    A --> H
    H --> I
