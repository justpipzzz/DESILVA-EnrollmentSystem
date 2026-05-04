package org.example;

import org.example.model.*;
import org.example.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        StudentRegistration studentService = new StudentRegistration();
        CourseRegistration courseService = new CourseRegistration();
        InstructorRegistration instructorService = new InstructorRegistration();
        EnrollmentService enrollmentService = new EnrollmentService();
        TuitionService tuitionService = new TuitionService();
        SectionRegistration sectionService = new SectionRegistration();
        DepartmentRegistration departmentService = new DepartmentRegistration();

        CampusRegistrar campusRegistrar = new CampusRegistrar(studentService, courseService, instructorService, enrollmentService, tuitionService, sectionService, departmentService);

        int nextStudentId = 1001;

        System.out.println("System Initialized. Welcome User.");

        while (true) {
            try {
                System.out.println("\n========================================");
                System.out.println("               MAIN MENU                ");
                System.out.println("========================================");
                System.out.println("[1] Manage Students");
                System.out.println("[2] Manage Courses");
                System.out.println("[3] Manage Instructors");
                System.out.println("[4] Manage Departments");
                System.out.println("[5] Enrollment Processing");
                System.out.println("[6] Tuition Management");
                System.out.println("[7] Exit");
                System.out.print("Select an option: ");
                int mainChoice = Integer.parseInt(input.nextLine());

                if (mainChoice == 7) {
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
                                int choice = Integer.parseInt(input.nextLine());

                                switch (choice) {
                                    case 1:
                                        List<Student> students = campusRegistrar.getAllStudents();
                                        if (students.isEmpty()) System.out.println("No records found.");
                                        else {
                                            System.out.println("\n[ Enrolled Students Directory ]");
                                            for (Student s : students) {
                                                System.out.println("ID: " + s.getPersonID() + " | Name: " + s.getFullName() +
                                                        " | Dept: " + s.getDepartment() + " | Prog: " + s.getProgram() +
                                                        " | Year: " + s.getYearLevel() + " | Sec: " + s.getSectionName());
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.print("Student ID to update: "); int studUPD = Integer.parseInt(input.nextLine());
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
                                        System.out.print("Student ID to delete: "); int studDLT = Integer.parseInt(input.nextLine());
                                        System.out.println(campusRegistrar.deleteStudent(new Student(studDLT, "", "", "", "", "", "", "")));
                                        break;
                                    case 4: studentMenu = false; break;
                                }
                            } catch (Exception e) { System.out.println("Error: Invalid Input."); }
                        }
                        break;

                    case 2:
                        boolean courseMenu = true;
                        while (courseMenu) {
                            try {
                                System.out.println("\n--- COURSE MANAGEMENT ---");
                                System.out.println("[1] Add Course\n[2] View All Courses\n[3] Update Course\n[4] Remove Course\n[5] Return");
                                System.out.print("Select an option: ");
                                int choice = Integer.parseInt(input.nextLine());

                                switch (choice) {
                                    case 1:
                                        System.out.print("Course ID: "); int courseID = Integer.parseInt(input.nextLine());
                                        System.out.print("Course Name: "); String courseName = input.nextLine();
                                        System.out.print("Units: "); int units = Integer.parseInt(input.nextLine());
                                        System.out.println(campusRegistrar.saveCourse(new Course(courseID, courseName, units)));
                                        break;
                                    case 2:
                                        List<Course> courses = campusRegistrar.getAllCourses();
                                        if (courses.isEmpty()) System.out.println("No records found.");
                                        else for (Course c : courses) System.out.println("ID: " + c.getCourseID() + " | " + c.getCourseName() + " (" + c.getUnits() + " units)");
                                        break;
                                    case 5: courseMenu = false; break;
                                    default: System.out.println("Feature locked or invalid.");
                                }
                            } catch (Exception e) { System.out.println("Error: Invalid Input."); }
                        }
                        break;

                    case 3:
                        boolean instructorMenu = true;
                        while (instructorMenu) {
                            try {
                                System.out.println("\n--- INSTRUCTOR MANAGEMENT ---");
                                System.out.println("[1] Register Instructor\n[2] View All Instructors\n[3] Return");
                                System.out.print("Select an option: ");
                                int choice = Integer.parseInt(input.nextLine());

                                switch (choice) {
                                    case 1:
                                        System.out.print("Instructor ID: "); int instID = Integer.parseInt(input.nextLine());
                                        System.out.print("Last Name: "); String lName = input.nextLine();
                                        System.out.print("First Name: "); String fName = input.nextLine();
                                        System.out.print("Middle Name: "); String mName = input.nextLine();
                                        System.out.print("Assigned Department: "); String dept = input.nextLine();
                                        System.out.println(campusRegistrar.saveInstructor(new Instructor(instID, lName, fName, mName, dept)));
                                        break;
                                    case 2:
                                        List<Instructor> instructors = campusRegistrar.getAllInstructors();
                                        if (instructors.isEmpty()) System.out.println("No records found.");
                                        else for (Instructor inst : instructors) System.out.println("ID: " + inst.getPersonID() + " | Name: " + inst.getFullName() + " | Dept: " + inst.getAssignedDepartment());
                                        break;
                                    case 3: instructorMenu = false; break;
                                }
                            } catch (Exception e) { System.out.println("Error: Invalid Input."); }
                        }
                        break;

                    case 4:
                        boolean hierarchyMenu = true;
                        while (hierarchyMenu) {
                            try {
                                System.out.println("\n--- MANAGE DEPARTMENTS ---");
                                System.out.println("[1] Create College Department");
                                System.out.println("[2] Add Program to Department");
                                System.out.println("[3] Add Block Section to Program");
                                System.out.println("[4] Assign Course & Instructor to a Section");
                                System.out.println("[5] View Full University Table");
                                System.out.println("[6] Return");
                                System.out.print("Select an option: ");
                                int choice = Integer.parseInt(input.nextLine());

                                switch (choice) {
                                    case 1:
                                        System.out.print("Enter Department Name (e.g., CITE): ");
                                        System.out.println(campusRegistrar.saveDepartment(new Department(input.nextLine())));
                                        break;
                                    case 2:
                                        System.out.println("\n[ Available Departments ]");
                                        for (Department d : campusRegistrar.getAllDepartments()) System.out.println("- " + d.getDepartmentName());
                                        System.out.print("\nType target Department Name: ");
                                        String targetDeptName = input.nextLine();
                                        Department deptFound = null;
                                        for (Department d : campusRegistrar.getAllDepartments()) if (d.getDepartmentName().equalsIgnoreCase(targetDeptName)) deptFound = d;
                                        if (deptFound != null) {
                                            System.out.print("Enter Program Name: ");
                                            deptFound.addProgram(new Program(input.nextLine()));
                                            System.out.println("Success: Program added.");
                                        } else System.out.println("Department not found.");
                                        break;
                                    case 3:
                                        System.out.println("\n[ Available Programs ]");
                                        for (Department d : campusRegistrar.getAllDepartments())
                                            for (Program p : d.getPrograms()) System.out.println("- " + p.getProgramName() + " (" + d.getDepartmentName() + ")");
                                        System.out.print("\nTarget Program Name: ");
                                        String targetProg = input.nextLine();
                                        Program progFound = null;
                                        for (Department d : campusRegistrar.getAllDepartments())
                                            for (Program p : d.getPrograms()) if (p.getProgramName().equalsIgnoreCase(targetProg)) progFound = p;
                                        if (progFound != null) {
                                            System.out.print("Section Name: "); String sName = input.nextLine();
                                            System.out.print("Max Capacity: "); int cap = Integer.parseInt(input.nextLine());
                                            Section newSec = new Section(sName, cap);
                                            progFound.addSection(newSec);
                                            campusRegistrar.saveSection(newSec);
                                            System.out.println("Success: Section added.");
                                        } else System.out.println("Program not found.");
                                        break;
                                    case 4:
                                        System.out.println("\n[ Available Sections ]");
                                        for (Section s : campusRegistrar.getAllSections()) System.out.println("- " + s.getSectionName());
                                        System.out.print("\nTarget Section Name: ");
                                        String targetSec = input.nextLine();
                                        Section secFound = null;
                                        for (Section s : campusRegistrar.getAllSections()) if (s.getSectionName().equalsIgnoreCase(targetSec)) secFound = s;
                                        if (secFound != null) {
                                            System.out.println("\n[ Available Courses ]");
                                            for (Course c : campusRegistrar.getAllCourses()) System.out.println("ID: " + c.getCourseID() + " | " + c.getCourseName());
                                            System.out.print("Enter Course ID: "); int cID = Integer.parseInt(input.nextLine());

                                            System.out.println("\n[ Available Instructors ]");
                                            for (Instructor i : campusRegistrar.getAllInstructors()) System.out.println("ID: " + i.getPersonID() + " | " + i.getFullName());
                                            System.out.print("Enter Instructor ID: "); int iID = Integer.parseInt(input.nextLine());

                                            Course cTarget = null; Instructor iTarget = null;
                                            for (Course c : campusRegistrar.getAllCourses()) if (c.getCourseID() == cID) cTarget = c;
                                            for (Instructor i : campusRegistrar.getAllInstructors()) if (i.getPersonID() == iID) iTarget = i;

                                            if (cTarget != null && iTarget != null) {
                                                secFound.assignCourseAndInstructor(cTarget, iTarget);
                                                System.out.println("Success: Assignment complete.");
                                            } else System.out.println("Invalid IDs provided.");
                                        }
                                        break;
                                    case 5:
                                        System.out.println("\n=================================================================================================================");
                                        System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n", "Department", "Program", "Section", "Capacity", "Course", "Instructor");
                                        System.out.println("-----------------------------------------------------------------------------------------------------------------");
                                        for (Department d : campusRegistrar.getAllDepartments())
                                            for (Program p : d.getPrograms())
                                                for (Section s : p.getSections()) {
                                                    if (s.getCourseInstructors().isEmpty())
                                                        System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n", d.getDepartmentName(), p.getProgramName(), s.getSectionName(), s.getEnrolledStudents().size() + "/" + s.getMaxCapacity(), "No Course", "Unassigned");
                                                    else for (Map.Entry<Course, Instructor> entry : s.getCourseInstructors().entrySet())
                                                        System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n", d.getDepartmentName(), p.getProgramName(), s.getSectionName(), s.getEnrolledStudents().size() + "/" + s.getMaxCapacity(), entry.getKey().getCourseName(), entry.getValue().getFullName());
                                                }
                                        System.out.println("=================================================================================================================");
                                        break;
                                    case 6: hierarchyMenu = false; break;
                                }
                            } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
                        }
                        break;

                    case 5:
                        try {
                            System.out.println("\n--- ENROLLMENT PROCESSING ---");
                            System.out.print("[1] Process Enrollment\n[2] Return\nChoice: ");
                            if (Integer.parseInt(input.nextLine()) == 1) {
                                System.out.println("\n[ Entering Student Details ]");
                                System.out.print("Last Name: "); String ln = input.nextLine();
                                System.out.print("First Name: "); String fn = input.nextLine();
                                System.out.print("Middle Name: "); String mn = input.nextLine();
                                System.out.print("Year Level: "); String yl = input.nextLine();

                                System.out.println("\n[ Available Block Sections ]");
                                for (Section s : campusRegistrar.getAllSections()) System.out.println("- " + s.getSectionName() + " | Seats: " + s.getEnrolledStudents().size() + "/" + s.getMaxCapacity());
                                System.out.print("Enter Section Name: "); String sn = input.nextLine();

                                Section targetSection = null;
                                for (Section s : campusRegistrar.getAllSections()) if (s.getSectionName().equalsIgnoreCase(sn)) targetSection = s;

                                if (targetSection != null) {
                                    String autoDept = "Unknown", autoProg = "Unknown";
                                    for (Department d : campusRegistrar.getAllDepartments())
                                        for (Program p : d.getPrograms()) if (p.getSections().contains(targetSection)) { autoDept = d.getDepartmentName(); autoProg = p.getProgramName(); }

                                    Student newStud = new Student(nextStudentId++, ln, fn, mn, autoDept, autoProg, yl, targetSection.getSectionName());
                                    newStud.addAllCourses(new ArrayList<>(targetSection.getCourseInstructors().keySet()));

                                    System.out.println(campusRegistrar.saveStudent(newStud));
                                    System.out.println(campusRegistrar.enrollStudent(newStud, targetSection));

                                    // IMPROVED SUMMARY WITH COURSES
                                    System.out.println("\n========================================");
                                    System.out.println("           ENROLLMENT SUMMARY           ");
                                    System.out.println("========================================");
                                    System.out.println("Student: " + newStud.getFullName() + " (ID: " + newStud.getPersonID() + ")");
                                    System.out.println("Path: " + autoDept + " -> " + autoProg + " -> " + targetSection.getSectionName());
                                    System.out.println("\nEnrolled Courses in this Block:");
                                    if (targetSection.getCourseInstructors().isEmpty()) {
                                        System.out.println("  (No courses linked to this section yet)");
                                    } else {
                                        for (Course c : targetSection.getCourseInstructors().keySet()) {
                                            System.out.println("  - " + c.getCourseName() + " (" + c.getUnits() + " units)");
                                        }
                                    }
                                    System.out.println("========================================");
                                } else System.out.println("Section not found.");
                            }
                        } catch (Exception e) { System.out.println("Enrollment Failed: " + e.getMessage()); }
                        break;

                    case 6:
                        try {
                            System.out.println("\n--- TUITION MANAGEMENT ---");
                            System.out.println("[1] Manage Student Account\n[2] Return");
                            if (Integer.parseInt(input.nextLine()) == 1) {
                                for (Student s : campusRegistrar.getAllStudents()) System.out.println("ID: " + s.getPersonID() + " | Name: " + s.getFullName());
                                System.out.print("Enter Student ID: ");
                                int targetId = Integer.parseInt(input.nextLine());
                                Student targetS = null;
                                for (Student s : campusRegistrar.getAllStudents()) if (s.getPersonID() == targetId) targetS = s;
                                if (targetS != null) {
                                    TuitionFeePayment acc = campusRegistrar.getTuitionAccount(targetS);
                                    boolean accMenu = true;
                                    while (accMenu) {
                                        System.out.println("\nAccount: " + targetS.getFullName());
                                        System.out.println("[1] Auto-Assess\n[2] Payment\n[3] Balance\n[4] Back");
                                        int accChoice = Integer.parseInt(input.nextLine());
                                        if (accChoice == 1) System.out.println(campusRegistrar.assessTuition(acc));
                                        else if (accChoice == 2) {
                                            System.out.print("Amount: ");
                                            System.out.println(campusRegistrar.processPayment(acc, Double.parseDouble(input.nextLine())));
                                        } else if (accChoice == 3) System.out.println("Balance: Php " + campusRegistrar.getBalance(acc));
                                        else accMenu = false;
                                    }
                                }
                            }
                        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
                        break;
                }
            } catch (Exception e) { System.out.println("Critical Error. Returning to main menu."); }
        }
    }
}