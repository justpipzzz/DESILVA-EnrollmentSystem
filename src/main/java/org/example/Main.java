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

        System.out.println("Student ID: " + s.getStudentID());
        System.out.println("Student Name: " + s.getStudentName());

    }
}