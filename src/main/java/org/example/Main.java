package org.example;

import org.example.model.*;
import org.example.service.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Initialize our concrete services
        StudentRegistration studentService = new StudentRegistration();
        CourseRegistration courseService = new CourseRegistration();
        InstructorRegistration instructorService = new InstructorRegistration();
        EnrollmentService enrollmentService = new EnrollmentService();
        TuitionService tuitionService = new TuitionService();

        // 2. Inject them into the Registrar
        CampusRegistrar campusRegistrar = new CampusRegistrar(studentService, courseService, instructorService, enrollmentService, tuitionService);

        // 3. Create mock data for Enrollment and Tuition testing
        Department ccsDepartment = new Department("College of Computer Studies");
        Course mockCourse = new Course(101, "Intro to Java", "BSIT");
        Section section1A = new Section("BSIT-1A", 2, mockCourse); // Max capacity of 2
        ccsDepartment.getSections().add(section1A);

        Student dummyStudent = new Student(999, "John Doe", "BSIT");
        TuitionFeePayment mockTuitionAccount = new TuitionFeePayment(dummyStudent);

        System.out.println("Welcome User!");

        while (true) {
            // ROBUST VALIDATION: Catching bad inputs on the main menu
            try {
                System.out.println("\nWhat will you do today?");
                System.out.println("[1] See STUDENT\n[2] See COURSES\n[3] See INSTRUCTORS\n[4] ENROLLMENT & HIERARCHY\n[5] TUITION MANAGEMENT\n[6] Exit");
                System.out.print("Enter your choice: ");
                int mainChoice = input.nextInt();

                if (mainChoice == 6) {
                    System.out.println("Exiting System. Goodbye!");
                    System.exit(0);
                }

                switch (mainChoice) {
                    case 1:
                        boolean studentMenu = true;
                        while (studentMenu) {
                            try { // ROBUST VALIDATION: Student Menu
                                System.out.println("\nChoose a STUDENT option below:");
                                System.out.println("[1] Save Student\n[2] Display Students\n[3] Update Student\n[4] Remove Student\n[5] Back to Main Menu");
                                System.out.print("Enter your choice: ");
                                int choice = input.nextInt();
                                input.nextLine(); // Consume newline

                                switch (choice) {
                                    case 1:
                                        System.out.println("Please input your STUDENT info below:");
                                        System.out.print("Student ID: ");
                                        int studID = input.nextInt();
                                        input.nextLine();
                                        System.out.print("Student Name: ");
                                        String studName = input.nextLine();
                                        System.out.print("Student Program: ");
                                        String studProgram = input.nextLine();

                                        System.out.println(campusRegistrar.saveStudent(new Student(studID, studName, studProgram)));
                                        break;
                                    case 2:
                                        List<Student> students = campusRegistrar.getAllStudents();
                                        if (students.isEmpty()) System.out.println("No students registered yet.");
                                        else {
                                            System.out.println("\n--- Registered Students ---");
                                            for (Student s : students) System.out.println("ID: " + s.getPersonID() + " | Name: " + s.getPersonName() + " | Program: " + s.getProgram());
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Student ID to update: ");
                                        int studUPD = input.nextInt();
                                        input.nextLine();
                                        System.out.print("New Student Name: ");
                                        studName = input.nextLine();
                                        System.out.print("New Student Program: ");
                                        studProgram = input.nextLine();
                                        System.out.println(campusRegistrar.updateStudent(new Student(studUPD, studName, studProgram)));
                                        break;
                                    case 4:
                                        System.out.print("Student ID to delete: ");
                                        int studDLT = input.nextInt();
                                        System.out.println(campusRegistrar.deleteStudent(new Student(studDLT)));
                                        break;
                                    case 5:
                                        studentMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Invalid input! Please enter valid numbers where requested.");
                                input.nextLine(); // Clear bad input
                            }
                        }
                        break;

                    case 2:
                        boolean courseMenu = true;
                        while (courseMenu) {
                            try { // ROBUST VALIDATION: Course Menu
                                System.out.println("\nChoose a COURSE option below:");
                                System.out.println("[1] Save Course\n[2] Display Courses\n[3] Update Course\n[4] Remove Course\n[5] Back to Main Menu");
                                System.out.print("Enter your choice: ");
                                int choice = input.nextInt();
                                input.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Course ID: ");
                                        int courseID = input.nextInt();
                                        input.nextLine();
                                        System.out.print("Course Name: ");
                                        String courseName = input.nextLine();
                                        System.out.print("Course Program: ");
                                        String courseProgram = input.nextLine();
                                        System.out.println(campusRegistrar.saveCourse(new Course(courseID, courseName, courseProgram)));
                                        break;
                                    case 2:
                                        List<Course> courses = campusRegistrar.getAllCourses();
                                        if (courses.isEmpty()) System.out.println("No courses registered yet.");
                                        else {
                                            System.out.println("\n--- Registered Courses ---");
                                            for (Course c : courses) System.out.println("ID: " + c.getCourseID() + " | Name: " + c.getCourseName() + " | Program: " + c.getProgram());
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Course ID to update: ");
                                        int courseUPD = input.nextInt();
                                        input.nextLine();
                                        System.out.print("New Course Name: ");
                                        courseName = input.nextLine();
                                        System.out.print("New Course Program: ");
                                        courseProgram = input.nextLine();
                                        System.out.println(campusRegistrar.updateCourse(new Course(courseUPD, courseName, courseProgram)));
                                        break;
                                    case 4:
                                        System.out.print("Course ID to delete: ");
                                        int courseDLT = input.nextInt();
                                        System.out.println(campusRegistrar.deleteCourse(new Course(courseDLT, "", "")));
                                        break;
                                    case 5:
                                        courseMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Invalid input! Please enter valid numbers where requested.");
                                input.nextLine(); // Clear bad input
                            }
                        }
                        break;

                    case 3:
                        boolean instructorMenu = true;
                        while (instructorMenu) {
                            try { // ROBUST VALIDATION: Instructor Menu
                                System.out.println("\nChoose an INSTRUCTOR option below:");
                                System.out.println("[1] Save Instructor\n[2] Display Instructors\n[3] Back to Main Menu");
                                System.out.print("Enter your choice: ");
                                int choice = input.nextInt();
                                input.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Instructor ID: ");
                                        int instID = input.nextInt();
                                        input.nextLine();
                                        System.out.print("Instructor Name: ");
                                        String instName = input.nextLine();
                                        System.out.println(campusRegistrar.saveInstructor(new Instructor(instID, instName)));
                                        break;
                                    case 2:
                                        List<Instructor> instructors = campusRegistrar.getAllInstructors();
                                        if (instructors.isEmpty()) System.out.println("No instructors registered yet.");
                                        else {
                                            System.out.println("\n--- Registered Instructors ---");
                                            for (Instructor inst : instructors) System.out.println("ID: " + inst.getPersonID() + " | Name: " + inst.getPersonName());
                                        }
                                        break;
                                    case 3:
                                        instructorMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Invalid input! Please enter valid numbers where requested.");
                                input.nextLine(); // Clear bad input
                            }
                        }
                        break;

                    case 4:
                        boolean enrollMenu = true;
                        while (enrollMenu) {
                            try { // ROBUST VALIDATION: Enrollment Menu
                                System.out.println("\nChoose an ENROLLMENT option below:");
                                System.out.println("[1] Enroll Student in BSIT-1A\n[2] View Department Hierarchy\n[3] Back to Main Menu");
                                System.out.print("Enter your choice: ");
                                int choice = input.nextInt();

                                switch (choice) {
                                    case 1:
                                        List<Student> currentStudents = campusRegistrar.getAllStudents();
                                        if (currentStudents.isEmpty()) {
                                            System.out.println("No students available. Register a student first.");
                                        } else {
                                            System.out.println("Available Students:");
                                            for (Student s : currentStudents) System.out.println("ID: " + s.getPersonID() + " | Name: " + s.getPersonName());

                                            System.out.print("Enter the ID of the student to enroll: ");
                                            int targetId = input.nextInt();
                                            Student studentToEnroll = null;
                                            for (Student s : currentStudents) {
                                                if (s.getPersonID() == targetId) { studentToEnroll = s; break; }
                                            }
                                            if (studentToEnroll != null) System.out.println(campusRegistrar.enrollStudent(studentToEnroll, section1A));
                                            else System.out.println("Student ID not found.");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("\n--- Current Department Status ---");
                                        System.out.println(campusRegistrar.getDepartmentHierarchy(ccsDepartment));
                                        break;
                                    case 3:
                                        enrollMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Invalid input! Please enter valid numbers where requested.");
                                input.nextLine(); // Clear bad input
                            }
                        }
                        break;

                    case 5:
                        boolean tuitionMenu = true;
                        while (tuitionMenu) {
                            try { // ROBUST VALIDATION: Tuition Menu
                                System.out.println("\nChoose a TUITION option for Student John Doe (ID: 999):");
                                System.out.println("[1] Assess Tuition (Enter Units)\n[2] Make Payment\n[3] Check Balance\n[4] Back to Main Menu");
                                System.out.print("Enter your choice: ");
                                int choice = input.nextInt();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Enter number of enrolled units: ");
                                        int units = input.nextInt();
                                        System.out.println(campusRegistrar.assessTuition(mockTuitionAccount, units));
                                        break;
                                    case 2:
                                        System.out.print("Enter payment amount: $");
                                        double amount = input.nextDouble();
                                        System.out.println(campusRegistrar.processPayment(mockTuitionAccount, amount));
                                        break;
                                    case 3:
                                        System.out.println("Current Total Fee: $" + mockTuitionAccount.getTotalTuitionFee());
                                        System.out.println("Amount Paid: $" + mockTuitionAccount.getAmountPaid());
                                        System.out.println("Remaining Balance: $" + campusRegistrar.getBalance(mockTuitionAccount));
                                        break;
                                    case 4:
                                        tuitionMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Invalid input! Please enter valid numbers where requested.");
                                input.nextLine(); // Clear bad input
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid main menu choice. Please select 1-6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input! Please enter a valid number from the menu.");
                input.nextLine(); // Clear the bad input so it doesn't loop infinitely
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                input.nextLine();
            }
        }
    }
}