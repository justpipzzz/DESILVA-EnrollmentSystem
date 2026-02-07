package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Student s = new Student();
        Course c = new Course();

        System.out.println("Please input your STUDENT info below:");
        System.out.print("Student ID: ");
        String studID = input.next();
        s.setStudentID(studID);

        System.out.print("Student Name: ");
        String studName = input.next();
        s.setStudentName(studName);

        input.next();

        System.out.print("Program: ");
        String studProgram = input.next();
        s.setProgram(studProgram);

        input.next();

        System.out.println("\nPlease input your COURSE info below:");
        System.out.print("Course ID: ");
        String courID = input.next();
        c.setCourseID(courID);

        System.out.print("Course Name: ");
        String courName = input.next();
        c.setCourseName(courName);

        input.next();
        System.out.print("Program: ");
        String courProgram = input.next();
        c.setProgram(courProgram);


        System.out.println("\nVERIFICATION\n");

        s.display();
        c.display();


    }
}