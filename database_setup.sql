-- Create database
CREATE DATABASE IF NOT EXISTS insurance_db;
USE insurance_db;

-- Create customer table
CREATE TABLE IF NOT EXISTS n_customer (
    cid INT PRIMARY KEY,
    cname VARCHAR(100) NOT NULL,
    address TEXT,
    contact VARCHAR(20),
    occupation VARCHAR(100),
    bname VARCHAR(100),
    accno VARCHAR(50)
);

-- Create policy table
CREATE TABLE IF NOT EXISTS n_policy (
    cid INT,
    policy_name VARCHAR(100),
    deposit_amount DECIMAL(10,2),
    monthly_payment DECIMAL(10,2),
    FOREIGN KEY (cid) REFERENCES n_customer(cid)
);

-- Insert sample data
INSERT INTO n_customer (cid, cname, address, contact, occupation, bname, accno) VALUES
(1, 'John Doe', '123 Main St, New York', '555-0101', 'Engineer', 'Chase Bank', 'ACC001'),
(2, 'Jane Smith', '456 Oak Ave, Los Angeles', '555-0102', 'Doctor', 'Bank of America', 'ACC002'),
(3, 'Bob Johnson', '789 Pine Rd, Chicago', '555-0103', 'Teacher', 'Wells Fargo', 'ACC003');

INSERT INTO n_policy (cid, policy_name, deposit_amount, monthly_payment) VALUES
(1, 'Life Insurance Premium', 50000.00, 1500.00),
(2, 'Health Insurance Plus', 30000.00, 1000.00),
(3, 'Vehicle Insurance', 20000.00, 800.00);
