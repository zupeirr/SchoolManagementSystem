# 🏫 School Management System

A robust **Console-based Application** built with **Java** and **MySQL** to manage student records.
This project implements **JDBC (Java Database Connectivity)** and the **DAO (Data Access Object) Design Pattern** for clean architecture and maintainability.

---

## 🚀 Features

*   **Add Student:** Register new students with Name, Grade, and Fees paid.
*   **View Students:** Fetch and display a list of all students from the database.
*   **Update Student:** Modify existing student details (Name, Grade, etc.).
*   **Delete Student:** Remove student records permanently from the database.
*   **Persistent Storage:** All data is stored securely in a MySQL database.

---

## 🛠️ Tech Stack

*   **Language:** Java (JDK 8+)
*   **Database:** MySQL
*   **Connectivity:** JDBC (MySQL Connector/J)
*   **Architecture:** MVC / DAO Pattern
*   **IDE:** IntelliJ IDEA / Eclipse / NetBeans

---

## 📦 How to Run

### Prerequisites
*   **Java Development Kit (JDK)** installed (Version 8 or higher).
*   **MySQL** installed and running.
*   **MySQL Connector/J** library added to your project.

### Steps
1.  Clone or download this repository to your local machine.
2.  Set up your MySQL database and edit the database configuration in the code if necessary.
3.  Compile the code:
    ```bash
    javac -cp .;mysql-connector-java.jar src/com/school/main/Main.java
    ```
4.  Run the application:
    ```bash
    java -cp .;mysql-connector-java.jar src/com/school/main/Main
    ```
    *(Replace `;` with `:` on Mac/Linux)*

---

## 🗂️ Project Structure

*   `dao/` - Contains `StudentDAO.java` for SQL operations.
*   `gui/` - MainGUI.java (the console or GUI interface).
*   `main/` - `Main.java` (program entry point).
*   `model/` - `Student.java` (data model/POJO).
*   `util/` - `DBConnection.java` (database connection logic).

---

## ⚠️ Important Notes

*   **Database:** Ensure you have created the required MySQL database and tables before running the app.
*   **Configuration:** Update database URL, username, and password as needed in `DBConnection.java`.
*   **JARs:** Make sure to add the JDBC driver (`mysql-connector-java.jar`) to your project's classpath.

---