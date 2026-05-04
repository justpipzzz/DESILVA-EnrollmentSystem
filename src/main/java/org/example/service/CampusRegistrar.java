package org.example.service;

import org.example.model.*;
import org.example.exception.DuplicateIDException;
import org.example.exception.SectionFullException;
import java.util.List;

public class CampusRegistrar {
    private final IStudentService studentService;
    private final ICourseService courseService;
    private final IInstructorService instructorService;
    private final IEnrollmentService enrollmentService;
    private final ITuitionService tuitionService;

    public CampusRegistrar(IStudentService studentService, ICourseService courseService,
                           IInstructorService instructorService, IEnrollmentService enrollmentService,
                           ITuitionService tuitionService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
        this.tuitionService = tuitionService;
    }

    // --- STUDENT METHODS ---
    public List<Student> getAllStudents() { return studentService.getAllStudents(); }

    public String saveStudent(Student student) {
        try {
            studentService.addStudent(student);
            return "Success: Student saved!";
        } catch (DuplicateIDException e) {
            return e.getMessage();
        }
    }

    public String updateStudent(Student student) {
        studentService.updateStudent(student);
        return "Success: Student updated!";
    }

    public String deleteStudent(Student student) {
        studentService.removeStudent(student);
        return "Success: Student removed!";
    }

    // --- COURSE METHODS ---
    public List<Course> getAllCourses() { return courseService.getAllCourses(); }

    public String saveCourse(Course course) {
        try {
            courseService.addCourse(course);
            return "Success: Course saved!";
        } catch (DuplicateIDException e) {
            return e.getMessage();
        }
    }

    public String updateCourse(Course course) {
        courseService.updateCourse(course);
        return "Success: Course updated!";
    }

    public String deleteCourse(Course course) {
        courseService.removeCourse(course);
        return "Success: Course removed!";
    }

    // --- INSTRUCTOR METHODS ---
    public List<Instructor> getAllInstructors() { return instructorService.getAllInstructors(); }

    public String saveInstructor(Instructor instructor) {
        try {
            instructorService.addInstructor(instructor);
            return "Success: Instructor saved!";
        } catch (DuplicateIDException e) {
            return e.getMessage();
        }
    }

    // --- ENROLLMENT METHODS ---
    public String enrollStudent(Student student, Section section) {
        try {
            enrollmentService.enrollStudentInSection(student, section);
            return "Success: " + student.getFullName() + " has been enrolled in " + section.getSectionName() + "!";
        } catch (SectionFullException e) {
            return e.getMessage();
        }
    }

    public String getDepartmentHierarchy(Department department) {
        return enrollmentService.viewDepartmentHierarchy(department);
    }

    // --- TUITION METHODS ---
    public TuitionFeePayment getTuitionAccount(Student student) {
        return tuitionService.getOrCreateAccount(student);
    }

    public String assessTuition(TuitionFeePayment payment, int units) {
        double total = tuitionService.calculateFee(payment, units);
        return "Tuition assessed: $" + total + " for " + units + " units.";
    }

    public String processPayment(TuitionFeePayment payment, double amount) {
        tuitionService.makePayment(payment, amount);
        double balance = tuitionService.getRemainingBalance(payment);
        return "Payment of $" + amount + " successful. Remaining Balance: $" + balance;
    }

    public double getBalance(TuitionFeePayment payment) {
        return tuitionService.getRemainingBalance(payment);
    }
}