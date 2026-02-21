package org.example;
import org.example.model.Course;
import org.example.model.Student;
import org.example.service.CourseRegistration;
import org.example.service.StudentRegistration;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        StudentRegistration sr = new StudentRegistration();
        CourseRegistration cr = new CourseRegistration();

        System.out.println("Welcome User!");
        System.out.println("What will you do today?");

        System.out.println("\n[1] See STUDENT\n[2] See COURSES\n[3] Exit");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                while(true){
                    System.out.println("\nChoose a STUDENT option below");
                    System.out.println("[1] Save Student\n[2] Display Students\n[3] Update Student\n[4] Remove Student\n[5] Exit");
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();

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

                            System.out.println("Student Added Successfully!");
                            sr.saveStudent(new Student(studID,  studName, studProgram));
                            break;
                        case 2:
                            sr.displayAllStudents();
                            break;
                        case 3:
                            System.out.println("Please input your STUDENT info to be updated below:");
                            System.out.print("Student ID: ");
                            int studUPD = input.nextInt();

                            System.out.println("Please input updated details below:");

                            System.out.print("Student Name: ");
                            studName = input.next();
                            input.nextLine();

                            System.out.print("Student Program: ");
                            studProgram = input.nextLine();

                            System.out.println("Student Update Successful!");
                            sr.updateStudent(new Student(studUPD, studName, studProgram));
                            break;
                        case 4:
                            System.out.println("Please input Student ID to be deleted:");
                            System.out.print("Student ID: ");
                            int studDLT = input.nextInt();
                            System.out.println("Student Delete Successful!");
                            sr.deleteStudent(new Student(studDLT));
                            break;
                        case 5:
                            System.exit(0);

                    }
                }
                break;
            case 2:
                while(true){
                    System.out.println("Choose a COURSE option below");
                    System.out.println("[1] Save Course\n[2] Display Courses\n[3] Update Course\n[4] Remove Course\n[5] Exit");
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();
                    switch(choice){
                        case 1:
                            System.out.println("Please input your COURSE info below:");
                            System.out.print("Course ID: ");
                            int courseID = input.nextInt();

                            System.out.print("Course Name: ");
                            String courseName = input.next();
                            input.nextLine();

                            System.out.print("Course Program: ");
                            String courseProgram = input.nextLine();

                            System.out.println("Course Added Successful!");
                            cr.saveCourse(new Course(courseID, courseName, courseProgram));
                            break;
                        case 2:
                            cr.displayAllCourses();
                            break;
                        case 3:
                            System.out.println("Please input Course ID to be updated:");
                            System.out.print("Course ID: ");
                            int courseUPD = input.nextInt();
                            System.out.println("Please input updated details below:");
                            System.out.print("Course Name: ");
                            courseName = input.next();
                            input.nextLine();
                            System.out.print("Course Program: ");
                            courseProgram = input.nextLine();
                            System.out.println("Course Update Successful!");
                            cr.updateCourse(new Course(courseUPD, courseName, courseProgram));
                            break;
                        case 4:
                            System.out.println("Please input Course ID to be deleted:");
                            System.out.print("Course ID: ");
                            int courseDLT = input.nextInt();
                            System.out.println("Course Delete Successful!");
                            break;
                        case 5:
                            System.exit(0);
                    }
                }
        }







    }
}