package com.school.gui;

import com.school.dao.StudentDAO;
import com.school.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainGUI extends JFrame {
    private JTextField txtName, txtGrade, txtFees;
    private JTable table;
    private DefaultTableModel model;
    private StudentDAO studentDAO;
    private int selectedId = -1;

    public MainGUI() {
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

        inputPanel.add(new JLabel("Grade:")); // Grade is now a String (e.g., "10th", "A")
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

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadStudents();

        // --- Button Logic ---

        // ADD BUTTON
        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String grade = txtGrade.getText(); // No parsing to int!
            double fees = Double.parseDouble(txtFees.getText());

            // This matches the Student(String, String, double) constructor
            Student s = new Student(name, grade, fees);
            studentDAO.addStudent(s);
            loadStudents();
            clearFields();
        });

        // UPDATE BUTTON (Fixes Error on Line 135)
        btnUpdate.addActionListener(e -> {
            if (selectedId != -1) {
                String name = txtName.getText();
                String grade = txtGrade.getText(); // No parsing to int!
                double fees = Double.parseDouble(txtFees.getText());

                // We wrap the data in a Student object
                Student s = new Student(selectedId, name, grade, fees);

                // We pass 1 argument (the object), not 3 arguments
                studentDAO.updateStudent(s);

                loadStudents();
                clearFields();
                selectedId = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Select a student to update!");
            }
        });

        // DELETE BUTTON
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

        // CLEAR BUTTON
        btnClear.addActionListener(e -> clearFields());

        // TABLE CLICK
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

    // Helper: Load Data (Fixes Error on Line 112)
    private void loadStudents() {
        model.setRowCount(0);
        List<Student> list = studentDAO.getAllStudents();
        for (Student s : list) {
            // We simply use s.getGrade() which is now a String
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}