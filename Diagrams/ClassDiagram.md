```mermaid
classDiagram
class Consumer {
    int consumerId
    String consumerName
    String address
    String phone
}
class WaterConnection {
    int connectionId
    int consumerId
    String connectionType
    Date connectionDate
    String status
}
class MeterReading {
    int readingId
    int connectionId
    Date readingDate
    decimal previousReading
    decimal currentReading
}
class WaterBill {
    int billId
    int readingId
    decimal unitsConsumed
    decimal billAmount
    Date dueDate
    String paymentStatus
}
class Payment {
    int paymentId
    int billId
    Date paymentDate
    decimal amountPaid
    String paymentMethod
}
Consumer "1" --> "many" WaterConnection
WaterConnection "1" --> "many" MeterReading
MeterReading "1" --> "many" WaterBill
WaterBill "1" --> "many" Payment```powershell
