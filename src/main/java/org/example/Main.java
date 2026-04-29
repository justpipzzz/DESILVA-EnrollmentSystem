package org.example;

import org.example.model.Course;
import org.example.model.Student;
import org.example.model.Instructor;
import org.example.service.CampusRegistrar;
import org.example.service.CourseRegistration;
import org.example.service.StudentRegistration;
import org.example.service.InstructorRegistration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Initialize our concrete services
        StudentRegistration studentService = new StudentRegistration();
        CourseRegistration courseService = new CourseRegistration();
        InstructorRegistration instructorService = new InstructorRegistration(); // NEW

        // 2. Inject them into the Registrar
        CampusRegistrar campusRegistrar = new CampusRegistrar(studentService, courseService, instructorService); // UPDATED

        System.out.println("Welcome User!");

        while (true) {
            System.out.println("\nWhat will you do today?");
            System.out.println("[1] See STUDENT\n[2] See COURSES\n[3] See INSTRUCTORS\n[4] Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = input.nextInt();

            if (mainChoice == 4) {
                System.out.println("Exiting System. Goodbye!");
                System.exit(0);
            }

            switch (mainChoice) {
                case 1:
                    boolean studentMenu = true;
                    while (studentMenu) {
                        System.out.println("\nChoose a STUDENT option below:");
                        System.out.println("[1] Save Student\n[2] Display Students\n[3] Update Student\n[4] Remove Student\n[5] Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int choice = input.nextInt();

                        switch (choice) {
                            case 1:
                                System.out.println("Please input your STUDENT info below:");
                                System.out.print("Student ID: ");
                                int studID = input.nextInt();
                                input.nextLine(); // Consume newline
                                System.out.print("Student Name: ");
                                String studName = input.nextLine();
                                System.out.print("Student Program: ");
                                String studProgram = input.nextLine();

                                // Notice how we print the String returned by the registrar!
                                String saveMsg = campusRegistrar.saveStudent(new Student(studID, studName, studProgram));
                                System.out.println(saveMsg);
                                break;
                            case 2:
                                List<Student> students = campusRegistrar.getAllStudents();
                                if (students.isEmpty()) {
                                    System.out.println("No students registered yet.");
                                } else {
                                    System.out.println("\n--- Registered Students ---");
                                    for (Student s : students) {
                                        // We use the getters to display the data here in the UI!
                                        System.out.println("ID: " + s.getPersonID() +
                                                " | Name: " + s.getPersonName() +
                                                " | Program: " + s.getProgram());
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Please input Student ID to be updated:");
                                System.out.print("Student ID: ");
                                int studUPD = input.nextInt();
                                input.nextLine(); // Consume newline
                                System.out.println("Please input updated details below:");
                                System.out.print("Student Name: ");
                                studName = input.nextLine();
                                System.out.print("Student Program: ");
                                studProgram = input.nextLine();

                                String updateMsg = campusRegistrar.updateStudent(new Student(studUPD, studName, studProgram));
                                System.out.println(updateMsg);
                                break;
                            case 4:
                                System.out.println("Please input Student ID to be deleted:");
                                System.out.print("Student ID: ");
                                int studDLT = input.nextInt();

                                // We just need an object with the matching ID to delete it
                                String deleteMsg = campusRegistrar.deleteStudent(new Student(studDLT));
                                System.out.println(deleteMsg);
                                break;
                            case 5:
                                studentMenu = false;
                                break;
                        }
                    }
                    break;

                case 2:
                    boolean courseMenu = true;
                    while (courseMenu) {
                        System.out.println("\nChoose a COURSE option below:");
                        System.out.println("[1] Save Course\n[2] Display Courses\n[3] Update Course\n[4] Remove Course\n[5] Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int choice = input.nextInt();

                        switch (choice) {
                            case 1:
                                System.out.println("Please input your COURSE info below:");
                                System.out.print("Course ID: ");
                                int courseID = input.nextInt();
                                input.nextLine(); // Consume newline
                                System.out.print("Course Name: ");
                                String courseName = input.nextLine();
                                System.out.print("Course Program: ");
                                String courseProgram = input.nextLine();

                                String saveMsg = campusRegistrar.saveCourse(new Course(courseID, courseName, courseProgram));
                                System.out.println(saveMsg);
                                break;
                            case 2:
                                List<Course> courses = campusRegistrar.getAllCourses();
                                if (courses.isEmpty()) {
                                    System.out.println("No courses registered yet.");
                                } else {
                                    System.out.println("\n--- Registered Courses ---");
                                    for (Course c : courses) {
                                        // Using the getters again here
                                        System.out.println("ID: " + c.getCourseID() +
                                                " | Name: " + c.getCourseName() +
                                                " | Program: " + c.getProgram());
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Please input Course ID to be updated:");
                                System.out.print("Course ID: ");
                                int courseUPD = input.nextInt();
                                input.nextLine(); // Consume newline
                                System.out.println("Please input updated details below:");
                                System.out.print("Course Name: ");
                                courseName = input.nextLine();
                                System.out.print("Course Program: ");
                                courseProgram = input.nextLine();

                                String updateMsg = campusRegistrar.updateCourse(new Course(courseUPD, courseName, courseProgram));
                                System.out.println(updateMsg);
                                break;
                            case 4:
                                System.out.println("Please input Course ID to be deleted:");
                                System.out.print("Course ID: ");
                                int courseDLT = input.nextInt();

                                // We just need an object with the matching ID to delete it
                                String deleteMsg = campusRegistrar.deleteCourse(new Course(courseDLT, "", ""));
                                System.out.println(deleteMsg);
                                break;
                            case 5:
                                courseMenu = false;
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean instructorMenu = true;
                    while (instructorMenu) {
                        System.out.println("\nChoose an INSTRUCTOR option below:");
                        System.out.println("[1] Save Instructor\n[2] Display Instructors\n[3] Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int choice = input.nextInt();

                        switch (choice) {
                            case 1:
                                System.out.println("Please input INSTRUCTOR info below:");
                                System.out.print("Instructor ID: ");
                                int instID = input.nextInt();
                                input.nextLine(); // Consume newline
                                System.out.print("Instructor Name: ");
                                String instName = input.nextLine();

                                String saveMsg = campusRegistrar.saveInstructor(new Instructor(instID, instName));
                                System.out.println(saveMsg);
                                break;
                            case 2:
                                List<Instructor> instructors = campusRegistrar.getAllInstructors();
                                if (instructors.isEmpty()) {
                                    System.out.println("No instructors registered yet.");
                                } else {
                                    System.out.println("\n--- Registered Instructors ---");
                                    for (Instructor inst : instructors) {
                                        // Using the getters from the Person parent class!
                                        System.out.println("ID: " + inst.getPersonID() + " | Name: " + inst.getPersonName());
                                    }
                                }
                                break;
                            case 3:
                                instructorMenu = false;
                                break;
                        }
                    }
                    break;
            }
        }
    }
}