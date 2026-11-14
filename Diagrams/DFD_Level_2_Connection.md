```mermaid
flowchart TB
    A[Municipal Administrator]

    B((2.1 Enter Connection Details))
    C((2.2 Validate Connection Details))
    D((2.3 Save Connection Details))
    E((2.4 View Connection Details))
    F((2.5 Update Connection Details))
    G((2.6 Delete Connection Details))

    H[(Water Connection Table)]

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
