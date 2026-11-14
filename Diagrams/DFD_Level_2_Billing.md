```mermaid
flowchart TB
    A[Municipal Administrator]

    B((4.1 Retrieve Meter Reading))
    C((4.2 Calculate Units Consumed))
    D((4.3 Calculate Bill Amount))
    E((4.4 Generate Water Bill))
    F((4.5 Save Bill Details))
    G((4.6 View Bill Details))

    H[(Water Bill Table)]
    I[(Meter Reading Table)]

    A --> B
    B <--> I
    B --> C
    C --> D
    D --> E
    E --> F
    F --> H
    H --> G
    A --> G
    G --> A
