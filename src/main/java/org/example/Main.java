package org.example;

import org.example.model.*;
import org.example.service.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        DepartmentRegistration departmentService = new DepartmentRegistration(); // NEW

        CampusRegistrar campusRegistrar = new CampusRegistrar(studentService, courseService, instructorService, enrollmentService, tuitionService, sectionService, departmentService);

        int nextStudentId = 1001;

        System.out.println("System Initialized. Welcome User.");

        while (true) {
            try {
                System.out.println("\n--- MAIN MENU ---");
                System.out.println("[1] Manage Students\n[2] Manage Courses\n[3] Manage Instructors");
                System.out.println("[4] Manage Hierarchy & Sections (Depts -> Programs -> Sections)");
                System.out.println("[5] Enrollment Processing\n[6] Tuition Management\n[7] Exit");
                System.out.print("Select an option: ");
                int mainChoice = input.nextInt();
                input.nextLine();

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
                                int choice = input.nextInt(); input.nextLine();

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
                                        System.out.print("Student ID to delete: "); int studDLT = input.nextInt(); input.nextLine();
                                        System.out.println(campusRegistrar.deleteStudent(new Student(studDLT, "", "", "", "", "", "", "")));
                                        break;
                                    case 4: studentMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) { System.out.println("Invalid input."); input.nextLine(); }
                        }
                        break;

                    case 2:
                        boolean courseMenu = true;
                        while (courseMenu) {
                            try {
                                System.out.println("\n--- COURSE MANAGEMENT ---");
                                System.out.println("[1] Add Course\n[2] View All Courses\n[3] Update Course\n[4] Remove Course\n[5] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt(); input.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Course ID: "); int courseID = input.nextInt(); input.nextLine();
                                        System.out.print("Course Name: "); String courseName = input.nextLine();
                                        System.out.print("Program Code: "); String courseProgram = input.nextLine();
                                        System.out.print("Units: "); int units = input.nextInt(); input.nextLine();
                                        System.out.println(campusRegistrar.saveCourse(new Course(courseID, courseName, courseProgram, units)));
                                        break;
                                    case 2:
                                        List<Course> courses = campusRegistrar.getAllCourses();
                                        if (courses.isEmpty()) System.out.println("No records found.");
                                        else for (Course c : courses) System.out.println("ID: " + c.getCourseID() + " | " + c.getCourseName() + " (" + c.getUnits() + " units)");
                                        break;
                                    case 3:
                                    case 4:
                                        System.out.println("Feature locked for brevity."); break;
                                    case 5: courseMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) { System.out.println("Invalid input."); input.nextLine(); }
                        }
                        break;

                    case 3:
                        boolean instructorMenu = true;
                        while (instructorMenu) {
                            try {
                                System.out.println("\n--- INSTRUCTOR MANAGEMENT ---");
                                System.out.println("[1] Register Instructor\n[2] View All Instructors\n[3] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt(); input.nextLine();

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
                                        else for (Instructor inst : instructors) System.out.println("ID: " + inst.getPersonID() + " | Name: " + inst.getFullName() + " | Dept: " + inst.getAssignedDepartment());
                                        break;
                                    case 3: instructorMenu = false; break;
                                }
                            } catch (InputMismatchException e) { System.out.println("Invalid input."); input.nextLine(); }
                        }
                        break;

                    case 4:
                        boolean hierarchyMenu = true;
                        while (hierarchyMenu) {
                            try {
                                System.out.println("\n--- MANAGE UNIVERSITY HIERARCHY ---");
                                System.out.println("[1] Create College Department");
                                System.out.println("[2] Add Program to Department");
                                System.out.println("[3] Add Block Section to Program");
                                System.out.println("[4] Assign Course & Instructor to a Section");
                                System.out.println("[5] View Full University Hierarchy");
                                System.out.println("[6] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt(); input.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Enter Department Name (e.g., CITE): ");
                                        String dName = input.nextLine();
                                        System.out.println(campusRegistrar.saveDepartment(new Department(dName)));
                                        break;
                                    case 2:
                                        if (campusRegistrar.getAllDepartments().isEmpty()) { System.out.println("Create a Department first!"); break; }
                                        System.out.println("Available Departments:");
                                        for (Department d : campusRegistrar.getAllDepartments()) System.out.println("- " + d.getDepartmentName());
                                        System.out.print("Type target Department Name: "); String targetDept = input.nextLine();

                                        Department foundDept = null;
                                        for (Department d : campusRegistrar.getAllDepartments()) if (d.getDepartmentName().equalsIgnoreCase(targetDept)) foundDept = d;

                                        if (foundDept != null) {
                                            System.out.print("Enter Program Name (e.g., BSIT): ");
                                            foundDept.addProgram(new Program(input.nextLine()));
                                            System.out.println("Program successfully added to " + foundDept.getDepartmentName());
                                        } else System.out.println("Department not found.");
                                        break;
                                    case 3:
                                        System.out.print("Enter target Program Name: "); String targetProg = input.nextLine();
                                        Program foundProg = null;
                                        for (Department d : campusRegistrar.getAllDepartments()) {
                                            for (Program p : d.getPrograms()) if (p.getProgramName().equalsIgnoreCase(targetProg)) foundProg = p;
                                        }
                                        if (foundProg != null) {
                                            System.out.print("Enter Section Name (e.g., IT1A): "); String secName = input.nextLine();
                                            System.out.print("Enter Max Capacity: "); int maxCap = input.nextInt(); input.nextLine();
                                            Section newSec = new Section(secName, maxCap);
                                            foundProg.addSection(newSec);
                                            campusRegistrar.saveSection(newSec); // Save to global registry too
                                            System.out.println("Section added to " + foundProg.getProgramName());
                                        } else System.out.println("Program not found.");
                                        break;
                                    case 4:
                                        System.out.print("Enter target Section Name: "); String targetSec = input.nextLine();
                                        Section foundSec = null;
                                        for (Section s : campusRegistrar.getAllSections()) if (s.getSectionName().equalsIgnoreCase(targetSec)) foundSec = s;

                                        if (foundSec != null) {
                                            System.out.print("Enter Course ID to add: "); int cID = input.nextInt(); input.nextLine();
                                            System.out.print("Enter Instructor ID to assign to this course: "); int iID = input.nextInt(); input.nextLine();

                                            Course cFound = null; Instructor iFound = null;
                                            for (Course c : campusRegistrar.getAllCourses()) if (c.getCourseID() == cID) cFound = c;
                                            for (Instructor i : campusRegistrar.getAllInstructors()) if (i.getPersonID() == iID) iFound = i;

                                            if (cFound != null && iFound != null) {
                                                foundSec.assignCourseAndInstructor(cFound, iFound);
                                                System.out.println("Successfully assigned " + iFound.getFullName() + " to teach " + cFound.getCourseName() + " for section " + foundSec.getSectionName());
                                            } else System.out.println("Invalid Course ID or Instructor ID.");
                                        } else System.out.println("Section not found.");
                                        break;
                                    case 5:
                                        System.out.println("\n[ UNIVERSITY HIERARCHY TREE ]");
                                        for (Department d : campusRegistrar.getAllDepartments()) {
                                            System.out.println("🏢 " + d.getDepartmentName());
                                            for (Program p : d.getPrograms()) {
                                                System.out.println(" ├── 🎓 " + p.getProgramName());
                                                for (Section s : p.getSections()) {
                                                    System.out.println(" │    ├── 📋 Section: " + s.getSectionName() + " (Capacity: " + s.getEnrolledStudents().size() + "/" + s.getMaxCapacity() + ")");
                                                    for (Map.Entry<Course, Instructor> entry : s.getCourseInstructors().entrySet()) {
                                                        System.out.println(" │    │    └── 📖 " + entry.getKey().getCourseName() + " taught by " + entry.getValue().getFullName());
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case 6: hierarchyMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) { System.out.println("Invalid input."); input.nextLine(); }
                        }
                        break;

                    case 5:
                        boolean enrollMenu = true;
                        while (enrollMenu) {
                            try {
                                System.out.println("\n--- ENROLLMENT PROCESSING ---");
                                System.out.println("[1] Process Student Enrollment\n[2] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt(); input.nextLine();

                                if (choice == 1) {
                                    List<Section> activeSections = campusRegistrar.getAllSections();
                                    if (activeSections.isEmpty()) {
                                        System.out.println("Error: No sections available.");
                                        break;
                                    }

                                    System.out.println("\n[ Entering Student Details ]");
                                    System.out.print("Enter Last Name: "); String lName = input.nextLine();
                                    System.out.print("Enter First Name: "); String fName = input.nextLine();
                                    System.out.print("Enter Middle Name: "); String mName = input.nextLine();
                                    System.out.print("Enter Year Level: "); String yearLvl = input.nextLine();

                                    System.out.println("\n[ Available Block Sections ]");
                                    for(Section s : activeSections) System.out.println("- " + s.getSectionName() + " | Seats: " + s.getEnrolledStudents().size() + "/" + s.getMaxCapacity());
                                    System.out.print("Enter Exact Section Name to Enroll: ");
                                    String selectedSecName = input.nextLine();

                                    Section targetSection = null;
                                    for(Section s : activeSections) if(s.getSectionName().equalsIgnoreCase(selectedSecName)) targetSection = s;

                                    if (targetSection == null) { System.out.println("Section not found. Enrollment aborted."); break; }

                                    // MAGIC: Search the hierarchy to auto-fill the student's Dept and Program!
                                    String autoDept = "Unknown";
                                    String autoProg = "Unknown";
                                    for (Department d : campusRegistrar.getAllDepartments()) {
                                        for (Program p : d.getPrograms()) {
                                            if (p.getSections().contains(targetSection)) {
                                                autoDept = d.getDepartmentName();
                                                autoProg = p.getProgramName();
                                            }
                                        }
                                    }

                                    int newStudId = nextStudentId++;
                                    Student newStudent = new Student(newStudId, lName, fName, mName, autoDept, autoProg, yearLvl, targetSection.getSectionName());

                                    // Extract all courses from the Map's keys and add them to the student
                                    newStudent.addAllCourses(new ArrayList<>(targetSection.getCourseInstructors().keySet()));

                                    try {
                                        System.out.println(campusRegistrar.saveStudent(newStudent));
                                        System.out.println(campusRegistrar.enrollStudent(newStudent, targetSection));
                                        System.out.println("\nEnrollment Summary:");
                                        System.out.println("Student: " + newStudent.getFullName() + " (Assigned ID: " + newStudent.getPersonID() + ")");
                                        System.out.println("Assigned to: " + autoDept + " -> " + autoProg + " -> " + targetSection.getSectionName());
                                    } catch (Exception e) { System.out.println("Enrollment Failed: " + e.getMessage()); }
                                } else if (choice == 2) enrollMenu = false;
                            } catch (InputMismatchException e) { System.out.println("Invalid input."); input.nextLine(); }
                        }
                        break;

                    case 6:
                        boolean tuitionMenu = true;
                        while (tuitionMenu) {
                            try {
                                System.out.println("\n--- TUITION MANAGEMENT ---");
                                System.out.println("[1] Manage Student Account\n[2] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt(); input.nextLine();

                                if (choice == 2) break;

                                if (choice == 1) {
                                    List<Student> activeStudents = campusRegistrar.getAllStudents();
                                    if (activeStudents.isEmpty()) { System.out.println("No registered students found in the system."); break; }

                                    for(Student s : activeStudents) System.out.println("ID: " + s.getPersonID() + " | Name: " + s.getFullName());
                                    System.out.print("Enter Student ID: ");
                                    int targetId = input.nextInt(); input.nextLine();

                                    Student targetStudent = null;
                                    for(Student s : activeStudents) if(s.getPersonID() == targetId) targetStudent = s;

                                    if (targetStudent == null) { System.out.println("Student ID not recognized."); break; }

                                    TuitionFeePayment account = campusRegistrar.getTuitionAccount(targetStudent);

                                    boolean accountMenu = true;
                                    while (accountMenu) {
                                        System.out.println("\nAccount Options for: " + targetStudent.getFullName());
                                        System.out.println("[1] Auto-Assess Tuition\n[2] Process Payment\n[3] View Balance\n[4] Back");
                                        System.out.print("Select action: ");
                                        int accChoice = input.nextInt(); input.nextLine();

                                        switch (accChoice) {
                                            case 1: System.out.println(campusRegistrar.assessTuition(account)); break;
                                            case 2:
                                                System.out.print("Enter payment amount: Php ");
                                                double amount = input.nextDouble(); input.nextLine();
                                                System.out.println(campusRegistrar.processPayment(account, amount));
                                                break;
                                            case 3:
                                                System.out.println("Total Assessed: Php " + account.getTotalTuitionFee());
                                                System.out.println("Total Paid: Php " + account.getAmountPaid());
                                                System.out.println("Outstanding Balance: Php " + campusRegistrar.getBalance(account));
                                                break;
                                            case 4: accountMenu = false; break;
                                        }
                                    }
                                }
                            } catch (InputMismatchException e) { System.out.println("Invalid input."); input.nextLine(); }
                        }
                        break;
                }
            } catch (InputMismatchException e) { System.out.println("Critical error."); input.nextLine(); }
        }
    }
}