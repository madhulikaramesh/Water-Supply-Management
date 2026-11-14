USE water_supply;

-- 1. Display all consumers
SELECT * FROM consumer;

-- 2. Display all water connections
SELECT * FROM water_connection;

-- 3. Display all meter readings
SELECT * FROM meter_reading;

-- 4. Display all water bills
SELECT * FROM water_bill;

-- 5. Display all payments
SELECT * FROM payment;

-- 6. Consumer and connection details
SELECT c.consumer_name, w.connection_type, w.status
FROM consumer c
JOIN water_connection w
ON c.consumer_id = w.consumer_id;

-- 7. Meter reading and consumption details
SELECT reading_id, connection_id,
       previous_reading, current_reading,
       (current_reading - previous_reading) AS calculated_units
FROM meter_reading;

-- 8. Bill details with payment status
SELECT bill_id, units_consumed, bill_amount,
       due_date, payment_status
FROM water_bill;

-- 9. Payment details with bill amount
SELECT p.payment_id, p.amount_paid,
       p.payment_method, b.bill_amount
FROM payment p
JOIN water_bill b ON p.bill_id = b.bill_id;

-- 10. Total payment collected
SELECT SUM(amount_paid) AS total_collection
FROM payment;
