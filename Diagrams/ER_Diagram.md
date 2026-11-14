
```mermaid
erDiagram
    CONSUMER ||--o{ WATER_CONNECTION : has
    WATER_CONNECTION ||--o{ METER_READING : records
    METER_READING ||--o{ WATER_BILL : generates
    WATER_BILL ||--o{ PAYMENT : receives

    CONSUMER {
        int consumer_id PK
        string consumer_name
        string address
        string phone
    }

    WATER_CONNECTION {
        int connection_id PK
        int consumer_id FK
        string connection_type
        date connection_date
        string status
    }

    METER_READING {
        int reading_id PK
        int connection_id FK
        date reading_date
        decimal previous_reading
        decimal current_reading
    }

    WATER_BILL {
        int bill_id PK
        int reading_id FK
        decimal units_consumed
        decimal bill_amount
        date due_date
        string payment_status
    }

    PAYMENT {
        int payment_id PK
        int bill_id FK
        date payment_date
        decimal amount_paid
        string payment_method
    }