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
        SectionRegistration sectionService = new SectionRegistration(); // NEW SERVICE

        CampusRegistrar campusRegistrar = new CampusRegistrar(studentService, courseService, instructorService, enrollmentService, tuitionService, sectionService);

        int nextStudentId = 1001;

        System.out.println("System Initialized. Welcome User.");

        while (true) {
            try {
                System.out.println("\n--- MAIN MENU ---");
                System.out.println("[1] Manage Students\n[2] Manage Courses\n[3] Manage Instructors\n[4] Manage Sections\n[5] Enrollment Processing\n[6] Tuition Management\n[7] Exit");
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
                                int choice = input.nextInt();
                                input.nextLine();

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
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected."); input.nextLine();
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
                                int choice = input.nextInt(); input.nextLine();

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
                                    case 5: courseMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected."); input.nextLine();
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
                                        else {
                                            for (Instructor inst : instructors) System.out.println("ID: " + inst.getPersonID() + " | Name: " + inst.getFullName() + " | Dept: " + inst.getAssignedDepartment());
                                        }
                                        break;
                                    case 3: instructorMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected."); input.nextLine();
                            }
                        }
                        break;

                    case 4:
                        boolean sectionMenu = true;
                        while (sectionMenu) {
                            try {
                                System.out.println("\n--- SECTION MANAGEMENT ---");
                                System.out.println("[1] Create New Section\n[2] View All Sections (Faculty Load)\n[3] Return");
                                System.out.print("Select an option: ");
                                int choice = input.nextInt(); input.nextLine();

                                switch (choice) {
                                    case 1:
                                        List<Course> activeCourses = campusRegistrar.getAllCourses();
                                        List<Instructor> activeInstructors = campusRegistrar.getAllInstructors();

                                        if (activeCourses.isEmpty() || activeInstructors.isEmpty()) {
                                            System.out.println("Error: You must have at least one Course and one Instructor registered before creating a Section!");
                                            break;
                                        }

                                        System.out.print("Enter Section Name (e.g., BSIT-1A): "); String secName = input.nextLine();
                                        System.out.print("Enter Department: "); String secDept = input.nextLine();
                                        System.out.print("Enter Program: "); String secProg = input.nextLine();
                                        System.out.print("Enter Max Capacity: "); int maxCap = input.nextInt(); input.nextLine();

                                        Section newSection = new Section(secName, secDept, secProg, maxCap);

                                        System.out.println("\n[ Select Course for this Section ]");
                                        for (Course c : activeCourses) System.out.println("ID: " + c.getCourseID() + " | " + c.getCourseName());
                                        System.out.print("Enter Course ID: "); int cId = input.nextInt(); input.nextLine();
                                        for (Course c : activeCourses) if (c.getCourseID() == cId) newSection.setCourse(c);

                                        System.out.println("\n[ Assign Instructor ]");
                                        for (Instructor i : activeInstructors) System.out.println("ID: " + i.getPersonID() + " | " + i.getFullName());
                                        System.out.print("Enter Instructor ID: "); int iId = input.nextInt(); input.nextLine();
                                        for (Instructor i : activeInstructors) if (i.getPersonID() == iId) newSection.setAssignedInstructor(i);

                                        if (newSection.getCourse() != null && newSection.getAssignedInstructor() != null) {
                                            System.out.println(campusRegistrar.saveSection(newSection));
                                        } else {
                                            System.out.println("Error: Invalid Course or Instructor ID. Section creation aborted.");
                                        }
                                        break;
                                    case 2:
                                        List<Section> sections = campusRegistrar.getAllSections();
                                        if (sections.isEmpty()) System.out.println("No sections found.");
                                        else {
                                            System.out.println("\n[ Active Sections & Faculty Load ]");
                                            for (Section s : sections) {
                                                String instructorName = (s.getAssignedInstructor() != null) ? s.getAssignedInstructor().getFullName() : "Unassigned";
                                                String courseName = (s.getCourse() != null) ? s.getCourse().getCourseName() : "No Course";
                                                System.out.println("Section: " + s.getSectionName() + " | Instructor: " + instructorName +
                                                        " | Course: " + courseName +
                                                        " | Capacity: [" + s.getEnrolledStudents().size() + "/" + s.getMaxCapacity() + "]");
                                            }
                                        }
                                        break;
                                    case 3: sectionMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected."); input.nextLine();
                            }
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

                                switch (choice) {
                                    case 1:
                                        List<Section> activeSections = campusRegistrar.getAllSections();
                                        if (activeSections.isEmpty()) {
                                            System.out.println("Error: No sections available. Please create a Section first.");
                                            break;
                                        }

                                        System.out.println("\n[ Entering Student Details ]");
                                        System.out.print("Enter Last Name: "); String lName = input.nextLine();
                                        System.out.print("Enter First Name: "); String fName = input.nextLine();
                                        System.out.print("Enter Middle Name: "); String mName = input.nextLine();
                                        System.out.print("Enter Department: "); String deptName = input.nextLine();
                                        System.out.print("Enter Program: "); String progName = input.nextLine();
                                        System.out.print("Enter Year Level: "); String yearLvl = input.nextLine();

                                        System.out.println("\n[ Available Sections ]");
                                        for(Section s : activeSections) {
                                            System.out.println("- " + s.getSectionName() + " (" + s.getCourse().getCourseName() + ") | Seats: " + s.getEnrolledStudents().size() + "/" + s.getMaxCapacity());
                                        }
                                        System.out.print("Enter Exact Section Name to Enroll: ");
                                        String selectedSecName = input.nextLine();

                                        Section targetSection = null;
                                        for(Section s : activeSections) {
                                            if(s.getSectionName().equalsIgnoreCase(selectedSecName)) { targetSection = s; break; }
                                        }

                                        if (targetSection == null) {
                                            System.out.println("Section not found. Enrollment aborted.");
                                            break;
                                        }

                                        int newStudId = nextStudentId++;
                                        Student newStudent = new Student(newStudId, lName, fName, mName, deptName, progName, yearLvl, targetSection.getSectionName());

                                        // Auto-add the course to the student so tuition calculates correctly!
                                        newStudent.addCourse(targetSection.getCourse());

                                        try {
                                            System.out.println(campusRegistrar.saveStudent(newStudent));
                                            System.out.println(campusRegistrar.enrollStudent(newStudent, targetSection));

                                            System.out.println("\nEnrollment Summary:");
                                            System.out.println("Student: " + newStudent.getFullName() + " (Assigned ID: " + newStudent.getPersonID() + ")");
                                            System.out.println("Enrolled in Section: " + targetSection.getSectionName() + " under " + targetSection.getAssignedInstructor().getFullName());
                                        } catch (Exception e) {
                                            System.out.println("Enrollment Failed: " + e.getMessage());
                                        }
                                        break;
                                    case 2: enrollMenu = false; break;
                                    default: System.out.println("Invalid selection.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected."); input.nextLine();
                            }
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
                                        System.out.println("[1] Auto-Assess Tuition\n[2] Process Payment\n[3] View Balance\n[4] Back");
                                        System.out.print("Select action: ");
                                        int accChoice = input.nextInt(); input.nextLine();

                                        switch (accChoice) {
                                            case 1:
                                                // No longer asks for units, automatically calculates!
                                                System.out.println(campusRegistrar.assessTuition(account));
                                                break;
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
                                            case 4:
                                                accountMenu = false;
                                                break;
                                        }
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input detected."); input.nextLine();
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid selection. Please use numbers 1-7.");
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