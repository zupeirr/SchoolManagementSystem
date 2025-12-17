package com.school.view;

import com.school.dao.StudentDAO;
import com.school.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class StudentFrame extends JFrame {
    private JTextField txtName, txtGrade, txtFees;
    private JTable table;
    private DefaultTableModel model;
    private StudentDAO studentDAO;
    private int selectedId = -1; // To hold ID of selected row for update/delete

    public StudentFrame() {
        studentDAO = new StudentDAO();
        setTitle("School Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Grade:"));
        txtGrade = new JTextField();
        inputPanel.add(txtGrade);

        inputPanel.add(new JLabel("Fees Paid:"));
        txtFees = new JTextField();
        inputPanel.add(txtFees);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // --- Table Panel ---
        String[] columns = {"ID", "Name", "Grade", "Fees"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to Frame
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load Data initially
        loadStudents();

        // --- Event Listeners ---

        // Add Student
        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String grade = txtGrade.getText();
            double fees = Double.parseDouble(txtFees.getText());

            Student s = new Student(name, grade, fees);
            studentDAO.addStudent(s);
            loadStudents();
            clearFields();
        });

        // Update Student
        btnUpdate.addActionListener(e -> {
            if (selectedId != -1) {
                String name = txtName.getText();
                String grade = txtGrade.getText();
                double fees = Double.parseDouble(txtFees.getText());

                Student s = new Student(selectedId, name, grade, fees);
                studentDAO.updateStudent(s);
                loadStudents();
                clearFields();
                selectedId = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Select a student to update!");
            }
        });

        // Delete Student
        btnDelete.addActionListener(e -> {
            if (selectedId != -1) {
                studentDAO.deleteStudent(selectedId);
                loadStudents();
                clearFields();
                selectedId = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Select a student to delete!");
            }
        });

        // Clear Fields
        btnClear.addActionListener(e -> clearFields());

        // Mouse Click on Table Row
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = (int) model.getValueAt(row, 0);
                txtName.setText(model.getValueAt(row, 1).toString());
                txtGrade.setText(model.getValueAt(row, 2).toString());
                txtFees.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    // Helper to load data from DB to JTable
    private void loadStudents() {
        model.setRowCount(0); // Clear table
        List<Student> list = studentDAO.getAllStudents();
        for (Student s : list) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getGrade(), s.getFeesPaid()});
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtGrade.setText("");
        txtFees.setText("");
        table.clearSelection();
        selectedId = -1;
    }
}