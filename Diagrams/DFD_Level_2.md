```mermaid
flowchart TB
    A[Municipal Administrator]

    B((1.1 Enter Consumer Details))
    C((1.2 Validate Consumer Details))
    D((1.3 Save Consumer Details))
    E((1.4 View Consumer Details))
    F((1.5 Update Consumer Details))
    G((1.6 Delete Consumer Details))

    H[(Consumer Table)]

    A --> B
    B --> C
    C -->|Valid Details| D
    C -->|Invalid Details| A
    D --> H
    H --> E
    A --> E
    A --> F
    F --> H
    A --> G
    G --> H
    E --> A
