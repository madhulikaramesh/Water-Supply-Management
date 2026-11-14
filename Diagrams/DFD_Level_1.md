```mermaid
flowchart TB
    A[Municipal Administrator]

    B((1.0 Consumer Management))
    C((2.0 Water Connection Management))
    D((3.0 Meter Reading Management))
    E((4.0 Billing Management))
    F((5.0 Payment Management))

    G[(MySQL Database)]

    A --> B
    A --> C
    A --> D
    A --> E
    A --> F

    B <--> G
    C <--> G
    D <--> G
    E <--> G
    F <--> G

    F --> A
