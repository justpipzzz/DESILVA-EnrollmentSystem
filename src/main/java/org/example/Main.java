package org.example;

import org.example.model.*;
import org.example.service.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Initialize Services
        StudentRegistration studentService = new StudentRegistration();
        CourseRegistration courseService = new CourseRegistration();
        InstructorRegistration instructorService = new InstructorRegistration();
        EnrollmentService enrollmentService = new EnrollmentService();
        TuitionService tuitionService = new TuitionService();
        SectionRegistration sectionService = new SectionRegistration();
        DepartmentRegistration departmentService = new DepartmentRegistration();

        CampusRegistrar campusRegistrar = new CampusRegistrar(
                studentService, courseService, instructorService,
                enrollmentService, tuitionService, sectionService, departmentService
        );

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
                    break;
                }

                switch (mainChoice) {
                    case 1: handleStudentMenu(input, campusRegistrar); break;
                    case 2: handleCourseMenu(input, campusRegistrar); break;
                    case 3: handleInstructorMenu(input, campusRegistrar); break;
                    case 4: handleDepartmentMenu(input, campusRegistrar); break;
                    case 5: handleEnrollment(input, campusRegistrar, nextStudentId++); break;
                    case 6: handleTuitionMenu(input, campusRegistrar); break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Critical Error: Invalid Input. Returning to Main Menu.");
            }
        }
    }

    // --- ENROLLMENT LOGIC (With Multi-Course Summary) ---
    private static void handleEnrollment(Scanner input, CampusRegistrar registrar, int newId) {
        try {
            System.out.println("\n--- ENROLLMENT PROCESSING ---");
            System.out.println("[1] Process Student Enrollment\n[2] Return");
            System.out.print("Select an option: ");
            if (Integer.parseInt(input.nextLine()) != 1) return;

            System.out.println("\n[ Entering Student Details ]");
            System.out.print("Enter Last Name: "); String ln = input.nextLine();
            System.out.print("Enter First Name: "); String fn = input.nextLine();
            System.out.print("Enter Middle Name: "); String mn = input.nextLine();
            System.out.print("Enter Year Level: "); String yl = input.nextLine();

            System.out.println("\n[ Available Block Sections ]");
            for (Section s : registrar.getAllSections()) {
                System.out.println("- " + s.getSectionName() + " | Seats: " + s.getEnrolledStudents().size() + "/" + s.getMaxCapacity());
            }

            System.out.print("Enter Exact Section Name to Enroll (or 'cancel'): ");
            String sn = input.nextLine();
            if (sn.equalsIgnoreCase("cancel")) return;

            Section targetSection = null;
            for (Section s : registrar.getAllSections()) {
                if (s.getSectionName().equalsIgnoreCase(sn)) targetSection = s;
            }

            if (targetSection != null) {
                String autoDept = "Unknown", autoProg = "Unknown";
                for (Department d : registrar.getAllDepartments()) {
                    for (Program p : d.getPrograms()) {
                        if (p.getSections().contains(targetSection)) {
                            autoDept = d.getDepartmentName();
                            autoProg = p.getProgramName();
                        }
                    }
                }

                Student newStud = new Student(newId, ln, fn, mn, autoDept, autoProg, yl, targetSection.getSectionName());

                // Link all courses currently in the block to the student
                newStud.addAllCourses(new ArrayList<>(targetSection.getCourseInstructors().keySet()));

                System.out.println(registrar.saveStudent(newStud));
                System.out.println(registrar.enrollStudent(newStud, targetSection));

                // SUCCESS SUMMARY
                System.out.println("\nEnrollment Summary:");
                System.out.println("Student: " + newStud.getFullName() + " (Assigned ID: " + newStud.getPersonID() + ")");
                System.out.println("Assigned to: " + autoDept + " -> " + autoProg + " -> " + targetSection.getSectionName());
                System.out.println("\nEnrolled Courses in this Block:");
                if (targetSection.getCourseInstructors().isEmpty()) {
                    System.out.println("  (No courses linked to this section yet)");
                } else {
                    for (Course c : targetSection.getCourseInstructors().keySet()) {
                        System.out.println("  - " + c.getCourseName() + " (" + c.getUnits() + " units)");
                    }
                }
            } else {
                System.out.println("Error: Section not found.");
            }
        } catch (Exception e) {
            System.out.println("Enrollment Failed: " + e.getMessage());
        }
    }

    // --- DEPARTMENT & HIERARCHY LOGIC ---
    private static void handleDepartmentMenu(Scanner input, CampusRegistrar registrar) {
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("\n--- MANAGE DEPARTMENTS ---");
                System.out.println("[1] Create College Department\n[2] Add Program to Department\n[3] Add Block Section to Program\n[4] Assign Course & Instructor to a Section\n[5] View Full University Table\n[6] Return");
                System.out.print("Select an option: ");
                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Department Name: ");
                        System.out.println(registrar.saveDepartment(new Department(input.nextLine())));
                        break;
                    case 5:
                        displayUniversityTable(registrar);
                        break;
                    case 6: loop = false; break;
                    // Note: Implementation for cases 2, 3, 4 would follow the same selection logic
                }
            } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        }
    }

    private static void displayUniversityTable(CampusRegistrar registrar) {
        System.out.println("\n=================================================================================================================");
        System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n", "Department", "Program", "Section", "Capacity", "Course", "Instructor");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (Department d : registrar.getAllDepartments()) {
            for (Program p : d.getPrograms()) {
                for (Section s : p.getSections()) {
                    if (s.getCourseInstructors().isEmpty()) {
                        System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n", d.getDepartmentName(), p.getProgramName(), s.getSectionName(), s.getEnrolledStudents().size() + "/" + s.getMaxCapacity(), "N/A", "Unassigned");
                    } else {
                        for (Map.Entry<Course, Instructor> entry : s.getCourseInstructors().entrySet()) {
                            System.out.printf("%-15s | %-15s | %-12s | %-10s | %-25s | %-20s%n", d.getDepartmentName(), p.getProgramName(), s.getSectionName(), s.getEnrolledStudents().size() + "/" + s.getMaxCapacity(), entry.getKey().getCourseName(), entry.getValue().getFullName());
                        }
                    }
                }
            }
        }
        System.out.println("=================================================================================================================");
    }

    // --- OTHER STUB METHODS FOR ORGANIZATION ---
    private static void handleStudentMenu(Scanner input, CampusRegistrar registrar) { /* Student logic */ }
    private static void handleCourseMenu(Scanner input, CampusRegistrar registrar) { /* Course logic */ }
    private static void handleInstructorMenu(Scanner input, CampusRegistrar registrar) { /* Instructor logic */ }
    private static void handleTuitionMenu(Scanner input, CampusRegistrar registrar) { /* Tuition logic */ }
}