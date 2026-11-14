CREATE DATABASE IF NOT EXISTS water_supply;
USE water_supply;

CREATE TABLE consumer (
    consumer_id INT NOT NULL AUTO_INCREMENT,
    consumer_name VARCHAR(100),
    address VARCHAR(200),
    phone VARCHAR(15),
    PRIMARY KEY (consumer_id)
);

CREATE TABLE water_connection (
    connection_id INT NOT NULL AUTO_INCREMENT,
    consumer_id INT,
    connection_type VARCHAR(50),
    connection_date DATE,
    status VARCHAR(20),
    PRIMARY KEY (connection_id),
    FOREIGN KEY (consumer_id) REFERENCES consumer(consumer_id)
);

CREATE TABLE meter_reading (
    reading_id INT NOT NULL AUTO_INCREMENT,
    connection_id INT,
    reading_date DATE,
    previous_reading DECIMAL(10,2),
    current_reading DECIMAL(10,2),
    PRIMARY KEY (reading_id),
    FOREIGN KEY (connection_id) REFERENCES water_connection(connection_id)
);

CREATE TABLE water_bill (
    bill_id INT NOT NULL AUTO_INCREMENT,
    reading_id INT,
    units_consumed DECIMAL(10,2),
    bill_amount DECIMAL(10,2),
    due_date DATE,
    payment_status VARCHAR(20),
    PRIMARY KEY (bill_id),
    FOREIGN KEY (reading_id) REFERENCES meter_reading(reading_id)
);

CREATE TABLE payment (
    payment_id INT NOT NULL AUTO_INCREMENT,
    bill_id INT,
    payment_date DATE,
    amount_paid DECIMAL(10,2),
    payment_method VARCHAR(30),
    PRIMARY KEY (payment_id),
    FOREIGN KEY (bill_id) REFERENCES water_bill(bill_id)
);
