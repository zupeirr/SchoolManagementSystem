package com.school.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.school.model.Student;
import com.school.util.DBConnection;

public class StudentDAO {

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, grade, fees_paid) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getName());
            pst.setString(2, student.getGrade()); // FIXED: setInt -> setString
            pst.setDouble(3, student.getFeesPaid());
            pst.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("grade"), // FIXED: getInt -> getString
                        rs.getDouble("fees_paid")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // FIXED: Method signature changed to accept a Student object
    // This fixes the error on line 95 of StudentFrame
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, grade=?, fees_paid=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getName());
            pst.setString(2, student.getGrade()); // FIXED: String type
            pst.setDouble(3, student.getFeesPaid()); // ADDED: Update fees
            pst.setInt(4, student.getId());

            if (pst.executeUpdate() > 0)
                System.out.println("Student updated successfully!");
            else
                System.out.println("Student not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            if (pst.executeUpdate() > 0)
                System.out.println("Student deleted successfully!");
            else
                System.out.println("Student not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}