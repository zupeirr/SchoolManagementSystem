package com.school.model;

public class Student {
    private int id;
    private String name;
    private int grade;
    private double feesPaid;

    public Student() {}

    public Student(int id, String name, int grade, double feesPaid) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.feesPaid = feesPaid;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }
    public double getFeesPaid() { return feesPaid; }
    public void setFeesPaid(double feesPaid) { this.feesPaid = feesPaid; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Grade: " + grade + " | Fees: $" + feesPaid;
    }
}