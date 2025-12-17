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

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Grade: ");
                    int grade = sc.nextInt();
                    System.out.print("Fees Paid: ");
                    double fees = sc.nextDouble();
                    dao.addStudent(new Student(0, name, grade, fees));
                    break;

                case 2:
                    List<Student> list = dao.getAllStudents();
                    list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String nname = sc.nextLine();
                    System.out.print("New Grade: ");
                    int ngrade = sc.nextInt();
                    dao.updateStudent(uid, nname, ngrade);
                    break;

                case 4:
                    System.out.print("Student ID: ");
                    dao.deleteStudent(sc.nextInt());
                    break;

                case 5:
                    sc.close();
                    System.exit(0);
            }
        }
    }
}