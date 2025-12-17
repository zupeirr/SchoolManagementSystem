CREATE DATABASE school_system;
USE school_system;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    grade INT NOT NULL,
    fees_paid DOUBLE DEFAULT 0.0
);