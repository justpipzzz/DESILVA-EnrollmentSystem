package org.example;
import org.example.model.Course;
import org.example.model.Student;
import org.example.service.StudentRegistration;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        StudentRegistration sr = new StudentRegistration();

        System.out.println("Welcome User!");
        System.out.println("Choose an option below");
        System.out.println("[1] Save Student\n[2] Display Students\n[3] Update Student\n[4] Remove Student\n[5] Exit");
        System.out.println("Enter your choice: ");
        int choice = input.nextInt();

        switch(choice){
            case 1:
                System.out.println("Please input your STUDENT info below:");
                System.out.print("Student ID: ");
                int studID = input.nextInt();

                System.out.print("Student Name: ");
                String studName = input.next();
                input.nextLine();

                System.out.print("Student Program: ");
                String studProgram = input.nextLine();

                sr.saveStudent(new Student(studID,  studName, studProgram));
                break;
            case 2:
                sr.displayAllStudents();
        }

        System.out.println("Please input your STUDENT info below:");
        System.out.print("Student ID: ");
        String studID = input.next();
        s.setStudentID(studID);

        System.out.println("\nPlease input your COURSE info below:");
        System.out.print("Course ID: ");
        String courID = input.next();
        c.setCourseID(courID);




        System.out.println("\nVERIFICATION\n");

        s.display();
        c.display();


    }
}