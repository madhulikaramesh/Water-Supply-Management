```mermaid
flowchart TB
    A[Municipal Administrator]

    subgraph W["Water Supply Management System"]
        B((Register Consumer))
        C((View Consumer Details))
        D((Edit Consumer))
        E((Delete Consumer))
        F((Manage Water Connection))
        G((Record Meter Reading))
        H((Calculate Consumption))
        I((Generate Water Bill))
        J((Record Payment))
        K((Edit Payment))
        L((Delete Payment))
        M((View Reports))
        N((Analyze Consumption))
    end

    A --- B
    A --- C
    A --- D
    A --- E
    A --- F
    A --- G
    A --- H
    A --- I
    A --- J
    A --- K
    A --- L
    A --- M
    A --- N
