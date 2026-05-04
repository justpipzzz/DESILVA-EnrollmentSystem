package org.example;

import org.example.model.*;
import org.example.service.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        StudentRegistration studentService = new StudentRegistration();
        CourseRegistration courseService = new CourseRegistration();
        InstructorRegistration instructorService = new InstructorRegistration();
        EnrollmentService enrollmentService = new EnrollmentService();
        TuitionService tuitionService = new TuitionService();

        CampusRegistrar campusRegistrar = new CampusRegistrar(studentService, courseService, instructorService, enrollmentService, tuitionService);

        // Auto-incrementing Student ID starting at 1001
        int nextStudentId = 1001;

        System.out.println("System Initialized. Welcome User.");

        while (true) {
            try {
                System.out.println("\n--- MAIN MENU ---");
                System.out.println("[1] Manage Students\n[2] Manage Courses\n[3] Manage Instructors\n[4] Enrollment Processing\n[5] Tuition Management\n[6] Exit");
                System.out.print("Select an option: ");
                int mainChoice = input.nextInt();
                input.nextLine();

                if (mainChoice == 6) {
                    System.out.println("Shutting down...");
                    System.exit(0);
                }

                switch (mainChoice) {
                    case 1:
                        boolean studentMenu = true;
                        while (studentMenu) {
                            try {
                                System.out.println("\n--- STUDENT MANAGEMENT ---");
                                System.out.println("[1] View All Students\n[2] Update Student\n[3] Remove Student\n[4] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt();
                                input.nextLine();

                                switch (choice) {
                                    case 1:
                                        List<Student> students = campusRegistrar.getAllStudents();
                                        if (students.isEmpty()) {
                                            System.out.println("No records found.");
                                        } else {
                                            System.out.println("\n[ Enrolled Students Directory ]");
                                            for (Student s : students) {
                                                // NOW PRINTING ALL THE DETAILS!
                                                System.out.println("ID: " + s.getPersonID() +
                                                        " | Name: " + s.getFullName() +
                                                        " | Dept: " + s.getDepartment() +
                                                        " | Program: " + s.getProgram() +
                                                        " | Year Level: " + s.getYearLevel() +
                                                        " | Section: " + s.getSectionName());
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.print("Student ID to update: "); int studUPD = input.nextInt(); input.nextLine();
                                        System.out.print("New Last Name: "); String lName = input.nextLine();
                                        System.out.print("New First Name: "); String fName = input.nextLine();
                                        System.out.print("New Middle Name: "); String mName = input.nextLine();
                                        System.out.print("New Department: "); String dept = input.nextLine();
                                        System.out.print("New Program: "); String prog = input.nextLine();
                                        System.out.print("New Year Level: "); String year = input.nextLine();
                                        System.out.print("New Section: "); String sec = input.nextLine();

                                        System.out.println(campusRegistrar.updateStudent(new Student(studUPD, lName, fName, mName, dept, prog, year, sec)));
                                        break;
                                    case 3:
                                        System.out.print("Student ID to delete: ");
                                        int studDLT = input.nextInt(); input.nextLine();
                                        System.out.println(campusRegistrar.deleteStudent(new Student(studDLT, "", "", "", "", "", "", "")));
                                        break;
                                    case 4:
                                        studentMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected.");
                                input.nextLine();
                            }
                        }
                        break;

                    case 2:
                        boolean courseMenu = true;
                        while (courseMenu) {
                            try {
                                System.out.println("\n--- COURSE MANAGEMENT ---");
                                System.out.println("[1] Add Course\n[2] View All Courses\n[3] Update Course\n[4] Remove Course\n[5] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt();
                                input.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Course ID: "); int courseID = input.nextInt(); input.nextLine();
                                        System.out.print("Course Name: "); String courseName = input.nextLine();
                                        System.out.print("Program: "); String courseProgram = input.nextLine();
                                        System.out.print("Units: "); int units = input.nextInt(); input.nextLine();
                                        System.out.println(campusRegistrar.saveCourse(new Course(courseID, courseName, courseProgram, units)));
                                        break;
                                    case 2:
                                        List<Course> courses = campusRegistrar.getAllCourses();
                                        if (courses.isEmpty()) System.out.println("No records found.");
                                        else {
                                            for (Course c : courses) System.out.println("ID: " + c.getCourseID() + " | " + c.getCourseName() + " (" + c.getUnits() + " units)");
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Course ID to update: "); int courseUPD = input.nextInt(); input.nextLine();
                                        System.out.print("New Course Name: "); courseName = input.nextLine();
                                        System.out.print("New Program: "); courseProgram = input.nextLine();
                                        System.out.print("New Units: "); units = input.nextInt(); input.nextLine();
                                        System.out.println(campusRegistrar.updateCourse(new Course(courseUPD, courseName, courseProgram, units)));
                                        break;
                                    case 4:
                                        System.out.print("Course ID to delete: "); int courseDLT = input.nextInt(); input.nextLine();
                                        System.out.println(campusRegistrar.deleteCourse(new Course(courseDLT, "", "", 0)));
                                        break;
                                    case 5:
                                        courseMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected.");
                                input.nextLine();
                            }
                        }
                        break;

                    case 3:
                        boolean instructorMenu = true;
                        while (instructorMenu) {
                            try {
                                System.out.println("\n--- INSTRUCTOR MANAGEMENT ---");
                                System.out.println("[1] Register Instructor\n[2] View All Instructors\n[3] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt();
                                input.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Instructor ID: "); int instID = input.nextInt(); input.nextLine();
                                        System.out.print("Last Name: "); String lName = input.nextLine();
                                        System.out.print("First Name: "); String fName = input.nextLine();
                                        System.out.print("Middle Name: "); String mName = input.nextLine();
                                        System.out.print("Assigned Department: "); String dept = input.nextLine();

                                        System.out.println(campusRegistrar.saveInstructor(new Instructor(instID, lName, fName, mName, dept)));
                                        break;
                                    case 2:
                                        List<Instructor> instructors = campusRegistrar.getAllInstructors();
                                        if (instructors.isEmpty()) System.out.println("No records found.");
                                        else {
                                            for (Instructor inst : instructors) System.out.println("ID: " + inst.getPersonID() + " | Name: " + inst.getFullName() + " | Dept: " + inst.getAssignedDepartment());
                                        }
                                        break;
                                    case 3:
                                        instructorMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected.");
                                input.nextLine();
                            }
                        }
                        break;

                    case 4:
                        boolean enrollMenu = true;
                        while (enrollMenu) {
                            try {
                                System.out.println("\n--- ENROLLMENT PROCESSING ---");
                                System.out.println("[1] Process Student Enrollment\n[2] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt();
                                input.nextLine();

                                switch (choice) {
                                    case 1:
                                        List<Course> activeCourses = campusRegistrar.getAllCourses();
                                        if (activeCourses.isEmpty()) {
                                            System.out.println("Error: No courses available. Please add courses before enrolling students.");
                                            break;
                                        }

                                        System.out.println("\n[ Entering Student Details ]");
                                        System.out.print("Enter Last Name: "); String lName = input.nextLine();
                                        System.out.print("Enter First Name: "); String fName = input.nextLine();
                                        System.out.print("Enter Middle Name: "); String mName = input.nextLine();
                                        System.out.print("Enter Department: "); String deptName = input.nextLine();
                                        System.out.print("Enter Program: "); String progName = input.nextLine();
                                        System.out.print("Enter Year Level: "); String yearLvl = input.nextLine();
                                        System.out.print("Enter Target Section (e.g., BSIT-1A): "); String secName = input.nextLine();

                                        System.out.println("\n[ Available Courses ]");
                                        for(Course c : activeCourses) {
                                            System.out.println("ID: " + c.getCourseID() + " | " + c.getCourseName() + " (" + c.getUnits() + " units)");
                                        }
                                        System.out.print("Enter Course ID to Enroll: ");
                                        int selectedCourseId = input.nextInt(); input.nextLine();

                                        Course targetCourse = null;
                                        for(Course c : activeCourses) {
                                            if(c.getCourseID() == selectedCourseId) { targetCourse = c; break; }
                                        }

                                        if (targetCourse == null) {
                                            System.out.println("Course selection invalid. Enrollment aborted.");
                                            break;
                                        }

                                        int newStudId = nextStudentId++;
                                        Student newStudent = new Student(newStudId, lName, fName, mName, deptName, progName, yearLvl, secName);
                                        Section targetSection = new Section(secName, 30, targetCourse);

                                        newStudent.addCourse(targetCourse);

                                        try {
                                            System.out.println(campusRegistrar.saveStudent(newStudent));
                                            System.out.println(campusRegistrar.enrollStudent(newStudent, targetSection));

                                            System.out.println("\nEnrollment Summary:");
                                            System.out.println("Student: " + newStudent.getFullName() + " (Assigned ID: " + newStudent.getPersonID() + ")");
                                            System.out.println("Enrolled in: " + targetCourse.getCourseName() + " (" + targetCourse.getUnits() + " Units)");
                                        } catch (Exception e) {
                                            System.out.println("Enrollment Failed: " + e.getMessage());
                                        }
                                        break;
                                    case 2:
                                        enrollMenu = false;
                                        break;
                                    default:
                                        System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected.");
                                input.nextLine();
                            }
                        }
                        break;

                    case 5:
                        boolean tuitionMenu = true;
                        while (tuitionMenu) {
                            try {
                                System.out.println("\n--- TUITION MANAGEMENT ---");
                                System.out.println("[1] Manage Student Account\n[2] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt();
                                input.nextLine();

                                if (choice == 2) break;

                                if (choice == 1) {
                                    List<Student> activeStudents = campusRegistrar.getAllStudents();
                                    if (activeStudents.isEmpty()) {
                                        System.out.println("No registered students found in the system.");
                                        break;
                                    }

                                    System.out.println("\n[ Select Student ]");
                                    for(Student s : activeStudents) {
                                        System.out.println("ID: " + s.getPersonID() + " | Name: " + s.getFullName());
                                    }
                                    System.out.print("Enter Student ID: ");
                                    int targetId = input.nextInt(); input.nextLine();

                                    Student targetStudent = null;
                                    for(Student s : activeStudents) {
                                        if(s.getPersonID() == targetId) { targetStudent = s; break; }
                                    }

                                    if (targetStudent == null) {
                                        System.out.println("Student ID not recognized.");
                                        break;
                                    }

                                    TuitionFeePayment account = campusRegistrar.getTuitionAccount(targetStudent);

                                    boolean accountMenu = true;
                                    while (accountMenu) {
                                        System.out.println("\nAccount Options for: " + targetStudent.getFullName());
                                        System.out.println("[1] Assess Tuition via Units\n[2] Process Payment\n[3] View Balance\n[4] Back");
                                        System.out.print("Select action: ");
                                        int accChoice = input.nextInt(); input.nextLine();

                                        switch (accChoice) {
                                            case 1:
                                                System.out.print("Enter units to assess: ");
                                                int units = input.nextInt(); input.nextLine();
                                                System.out.println(campusRegistrar.assessTuition(account, units));
                                                break;
                                            case 2:
                                                System.out.print("Enter payment amount: $");
                                                double amount = input.nextDouble(); input.nextLine();
                                                System.out.println(campusRegistrar.processPayment(account, amount));
                                                break;
                                            case 3:
                                                System.out.println("Total Assessed: $" + account.getTotalTuitionFee());
                                                System.out.println("Total Paid: $" + account.getAmountPaid());
                                                System.out.println("Outstanding Balance: $" + campusRegistrar.getBalance(account));
                                                break;
                                            case 4:
                                                accountMenu = false;
                                                break;
                                        }
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected.");
                                input.nextLine();
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid selection. Please use numbers 1-6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Critical error: Invalid input format.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("System exception: " + e.getMessage());
                input.nextLine();
            }
        }
    }
}