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
                            } catch (Exception e) { System.out.println("Invalid input. Try again."); input.nextLine(); }
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
                                        System.out.print("Units: "); int units = input.nextInt(); input.nextLine();
                                        System.out.println(campusRegistrar.saveCourse(new Course(courseID, courseName, units)));
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
                            } catch (Exception e) { System.out.println("Invalid input. Try again."); input.nextLine(); }
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
                            } catch (Exception e) { System.out.println("Invalid input. Try again."); input.nextLine(); }
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
                                int choice = input.nextInt(); input.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Enter Department Name (e.g., CITE): ");
                                        String dName = input.nextLine();
                                        System.out.println(campusRegistrar.saveDepartment(new Department(dName)));
                                        break;
                                    case 2:
                                        if (campusRegistrar.getAllDepartments().isEmpty()) { System.out.println("Create a Department first!"); break; }

                                        Department foundDept = null;
                                        while (foundDept == null) {
                                            System.out.println("\n[ Available Departments ]");
                                            for (Department d : campusRegistrar.getAllDepartments()) System.out.println("- " + d.getDepartmentName());

                                            System.out.print("\nType target Department Name (or 'cancel'): ");
                                            String targetDept = input.nextLine();
                                            if (targetDept.equalsIgnoreCase("cancel")) break;

                                            for (Department d : campusRegistrar.getAllDepartments()) if (d.getDepartmentName().equalsIgnoreCase(targetDept)) foundDept = d;
                                            if (foundDept == null) System.out.println("Department not found. Please check spelling.");
                                        }

                                        if (foundDept != null) {
                                            System.out.print("Enter Program Name (e.g., BSIT): ");
                                            foundDept.addProgram(new Program(input.nextLine()));
                                            System.out.println("Program successfully added to " + foundDept.getDepartmentName());
                                        }
                                        break;
                                    case 3:
                                        Program foundProg = null;
                                        while (foundProg == null) {
                                            System.out.println("\n[ Available Programs ]");
                                            boolean hasPrograms = false;
                                            for (Department d : campusRegistrar.getAllDepartments()) {
                                                for (Program p : d.getPrograms()) {
                                                    System.out.println("- " + p.getProgramName() + " (under " + d.getDepartmentName() + ")");
                                                    hasPrograms = true;
                                                }
                                            }
                                            if (!hasPrograms) {
                                                System.out.println("No programs exist yet. Please add a program first.");
                                                break;
                                            }

                                            System.out.print("\nEnter target Program Name (or 'cancel'): ");
                                            String targetProg = input.nextLine();
                                            if (targetProg.equalsIgnoreCase("cancel")) break;

                                            for (Department d : campusRegistrar.getAllDepartments()) {
                                                for (Program p : d.getPrograms()) if (p.getProgramName().equalsIgnoreCase(targetProg)) foundProg = p;
                                            }
                                            if (foundProg == null) System.out.println("Program not found. Please check spelling.");
                                        }

                                        if (foundProg != null) {
                                            System.out.print("Enter Section Name (e.g., IT1A): "); String secName = input.nextLine();
                                            System.out.print("Enter Max Capacity: "); int maxCap = input.nextInt(); input.nextLine();
                                            Section newSec = new Section(secName, maxCap);
                                            foundProg.addSection(newSec);
                                            campusRegistrar.saveSection(newSec);
                                            System.out.println("Section added to " + foundProg.getProgramName());
                                        }
                                        break;
                                    case 4:
                                        Section foundSec = null;
                                        while (foundSec == null) {
                                            System.out.println("\n[ Available Sections ]");
                                            List<Section> allSections = campusRegistrar.getAllSections();
                                            if (allSections.isEmpty()) {
                                                System.out.println("No sections exist yet. Please add a section first.");
                                                break;
                                            }
                                            for (Section s : allSections) {
                                                System.out.println("- " + s.getSectionName());
                                            }

                                            System.out.print("\nEnter target Section Name (or 'cancel'): ");
                                            String targetSec = input.nextLine();
                                            if (targetSec.equalsIgnoreCase("cancel")) break;

                                            for (Section s : allSections) if (s.getSectionName().equalsIgnoreCase(targetSec)) foundSec = s;
                                            if (foundSec == null) System.out.println("Section not found. Please check spelling.");
                                        }

                                        if (foundSec != null) {
                                            Course cFound = null; Instructor iFound = null;

                                            while(cFound == null) {
                                                System.out.println("\n[ Available Courses ]");
                                                for (Course c : campusRegistrar.getAllCourses()) {
                                                    System.out.println("ID: " + c.getCourseID() + " | " + c.getCourseName());
                                                }

                                                System.out.print("\nEnter Course ID to add (or 0 to cancel): ");
                                                int cID = input.nextInt(); input.nextLine();
                                                if (cID == 0) break;
                                                for (Course c : campusRegistrar.getAllCourses()) if (c.getCourseID() == cID) cFound = c;
                                                if (cFound == null) System.out.println("Course ID not found.");
                                            }
                                            if (cFound == null) break;

                                            while(iFound == null) {
                                                System.out.println("\n[ Available Instructors ]");
                                                for (Instructor i : campusRegistrar.getAllInstructors()) {
                                                    System.out.println("ID: " + i.getPersonID() + " | " + i.getFullName());
                                                }

                                                System.out.print("\nEnter Instructor ID to assign (or 0 to cancel): ");
                                                int iID = input.nextInt(); input.nextLine();
                                                if (iID == 0) break;
                                                for (Instructor i : campusRegistrar.getAllInstructors()) if (i.getPersonID() == iID) iFound = i;
                                                if (iFound == null) System.out.println("Instructor ID not found.");
                                            }
                                            if (iFound == null) break;

                                            foundSec.assignCourseAndInstructor(cFound, iFound);
                                            System.out.println("Successfully assigned " + iFound.getFullName() + " to teach " + cFound.getCourseName() + " for " + foundSec.getSectionName());
                                        }
                                        break;
                                    case 5:
                                        System.out.println("\n=================================================================================================================");
                                        System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n", "Department", "Program", "Section", "Capacity", "Course", "Instructor");
                                        System.out.println("-----------------------------------------------------------------------------------------------------------------");

                                        boolean hasData = false;
                                        for (Department d : campusRegistrar.getAllDepartments()) {
                                            for (Program p : d.getPrograms()) {
                                                for (Section s : p.getSections()) {
                                                    hasData = true;
                                                    if (s.getCourseInstructors().isEmpty()) {
                                                        System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n",
                                                                d.getDepartmentName(), p.getProgramName(), s.getSectionName(),
                                                                s.getEnrolledStudents().size() + "/" + s.getMaxCapacity(), "No Course Added", "Unassigned");
                                                    } else {
                                                        for (Map.Entry<Course, Instructor> entry : s.getCourseInstructors().entrySet()) {
                                                            System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n",
                                                                    d.getDepartmentName(), p.getProgramName(), s.getSectionName(),
                                                                    s.getEnrolledStudents().size() + "/" + s.getMaxCapacity(),
                                                                    entry.getKey().getCourseName(), entry.getValue().getFullName());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!hasData) {
                                            System.out.println("No department records found. Please create the hierarchy first.");
                                        }
                                        System.out.println("=================================================================================================================");
                                        break;
                                    case 6: hierarchyMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (Exception e) { System.out.println("Invalid input. Try again."); input.nextLine(); }
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

                                    Section targetSection = null;
                                    while (targetSection == null) {
                                        System.out.print("\nEnter Exact Section Name to Enroll (or 'cancel'): ");
                                        String selectedSecName = input.nextLine();
                                        if(selectedSecName.equalsIgnoreCase("cancel")) break;

                                        for(Section s : activeSections) if(s.getSectionName().equalsIgnoreCase(selectedSecName)) targetSection = s;
                                        if (targetSection == null) System.out.println("Section not found. Please try again.");
                                    }
                                    if (targetSection == null) break;

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
                                    newStudent.addAllCourses(new ArrayList<>(targetSection.getCourseInstructors().keySet()));

                                    try {
                                        System.out.println(campusRegistrar.saveStudent(newStudent));
                                        System.out.println(campusRegistrar.enrollStudent(newStudent, targetSection));
                                        System.out.println("\nEnrollment Summary:");
                                        System.out.println("Student: " + newStudent.getFullName() + " (Assigned ID: " + newStudent.getPersonID() + ")");
                                        System.out.println("Assigned to: " + autoDept + " -> " + autoProg + " -> " + targetSection.getSectionName());

                                        // MODIFIED: Including the specific courses linked to the section
                                        System.out.println("Courses Linked to Section:");
                                        if (targetSection.getCourseInstructors().isEmpty()) {
                                            System.out.println("- No courses assigned to this section yet.");
                                        } else {
                                            for (Course c : targetSection.getCourseInstructors().keySet()) {
                                                System.out.println("- " + c.getCourseName() + " (" + c.getUnits() + " units)");
                                            }
                                        }
                                    } catch (Exception e) { System.out.println("Enrollment Failed: " + e.getMessage()); }
                                } else if (choice == 2) enrollMenu = false;
                            } catch (Exception e) { System.out.println("Invalid input. Try again."); input.nextLine(); }
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

                                    Student targetStudent = null;
                                    while (targetStudent == null) {
                                        System.out.println("\n[ Select Student ]");
                                        for(Student s : activeStudents) System.out.println("ID: " + s.getPersonID() + " | Name: " + s.getFullName());
                                        System.out.print("\nEnter Student ID (or 0 to cancel): ");
                                        int targetId = input.nextInt(); input.nextLine();
                                        if(targetId == 0) break;

                                        for(Student s : activeStudents) if(s.getPersonID() == targetId) targetStudent = s;
                                        if (targetStudent == null) System.out.println("Student ID not recognized. Try again.");
                                    }
                                    if (targetStudent == null) break;

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
                            } catch (Exception e) { System.out.println("Invalid input. Try again."); input.nextLine(); }
                        }
                        break;
                }
            } catch (Exception e) { System.out.println("Critical error. Invalid format. Try again."); input.nextLine(); }
        }
    }
}