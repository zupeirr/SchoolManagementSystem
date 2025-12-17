# 🏫 School Management System

A robust **Console-based Application** built with **Java** and **MySQL** to manage student records. This project demonstrates the implementation of **JDBC (Java Database Connectivity)** and the **DAO (Data Access Object) Design Pattern** for clean architecture.

## 🚀 Features
- **Add Student**: Register new students with Name, Grade, and Fees paid.
- **View Students**: Fetch and display a list of all students from the database.
- **Update Student**: Modify existing student details (Name, Grade, etc.).
- **Delete Student**: Remove student records permanently from the database.
- **Persistent Storage**: All data is stored securely in a MySQL database.

## 🛠️ Tech Stack
- **Language**: Java (JDK 8+)
- **Database**: MySQL
- **Connectivity**: JDBC (MySQL Connector/J)
- **Architecture**: MVC / DAO Pattern
- **IDE**: IntelliJ IDEA / Eclipse / NetBeans

## 📂 Project Structure
The project follows a modular structure for better maintainability:
```text
src/com/school
├── dao/          # Database logic (CRUD operations)
├── model/        # Student object (Getters/Setters)
├── util/         # Database connection helper (Singleton-like)
└── main/         # Main application entry point (Console UI)
