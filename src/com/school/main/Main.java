package com.school.main;

import java.util.List;
import java.util.Scanner;

import com.school.dao.StudentDAO;
import com.school.model.Student;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SCHOOL MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            // Check if input is an integer to prevent crash
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // clear invalid input
                continue;
            }

            int ch = sc.nextInt();
            sc.nextLine(); // Consume the newline character left by nextInt()

            switch (ch) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Grade (e.g., 10th, A): ");
                    String grade = sc.nextLine(); // FIXED: Read as String

                    System.out.print("Fees Paid: ");
                    double fees = sc.nextDouble();

                    // FIXED: Use constructor (String, String, double)
                    dao.addStudent(new Student(name, grade, fees));
                    break;

                case 2:
                    List<Student> list = dao.getAllStudents();
                    if (list.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("------------------------------------------------");
                        for (Student s : list) {
                            System.out.println(s);
                        }
                        System.out.println("------------------------------------------------");
                    }
                    break;

                case 3:
                    System.out.print("Enter ID of Student to Update: ");
                    int uid = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    System.out.print("New Name: ");
                    String nname = sc.nextLine();

                    System.out.print("New Grade: ");
                    String ngrade = sc.nextLine(); // FIXED: Read as String

                    System.out.print("New Fees Paid: ");
                    double nfees = sc.nextDouble();

                    // FIXED: Pass a Student object to updateStudent
                    dao.updateStudent(new Student(uid, nname, ngrade, nfees));
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int delId = sc.nextInt();
                    dao.deleteStudent(delId);
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}