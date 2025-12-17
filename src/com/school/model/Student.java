package com.school.model;

public class Student {
    private int id;
    private String name;
    private String grade; // CHANGED: int -> String (To match DB and GUI)
    private double feesPaid;

    // Default Constructor
    public Student() {}

    // 1. ADDED: Constructor for creating new students (No ID required)
    // This fixes the "Cannot resolve constructor" error on line 81 of StudentFrame
    public Student(String name, String grade, double feesPaid) {
        this.name = name;
        this.grade = grade;
        this.feesPaid = feesPaid;
    }

    // 2. UPDATED: Constructor for fetching/updating students
    // Changed 'int grade' to 'String grade' to fix error on line 94 of StudentFrame
    public Student(int id, String name, String grade, double feesPaid) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.feesPaid = feesPaid;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; } // Changed to String

    public double getFeesPaid() { return feesPaid; }
    public void setFeesPaid(double feesPaid) { this.feesPaid = feesPaid; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Grade: " + grade + " | Fees: $" + feesPaid;
    }
}