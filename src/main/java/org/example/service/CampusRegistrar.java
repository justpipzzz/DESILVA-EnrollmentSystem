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
    private final ISectionService sectionService;
    private final IDepartmentService departmentService;

    public CampusRegistrar(IStudentService studentService, ICourseService courseService,
                           IInstructorService instructorService, IEnrollmentService enrollmentService,
                           ITuitionService tuitionService, ISectionService sectionService,
                           IDepartmentService departmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
        this.tuitionService = tuitionService;
        this.sectionService = sectionService;
        this.departmentService = departmentService;
    }

    // --- STUDENT METHODS ---
    public List<Student> getAllStudents() { return studentService.getAllStudents(); }
    public void saveStudent(Student student) throws DuplicateIDException { studentService.addStudent(student); }
    public void updateStudent(Student student) { studentService.updateStudent(student); }
    public void deleteStudent(Student student) { studentService.removeStudent(student); }

    // --- COURSE METHODS (CRUD) ---
    public List<Course> getAllCourses() { return courseService.getAllCourses(); }
    public void saveCourse(Course course) throws DuplicateIDException { courseService.addCourse(course); }
    public void updateCourse(Course course) { courseService.updateCourse(course); }
    public void deleteCourse(Course course) { courseService.removeCourse(course); }
    public Course findCourseById(int id) {
        return courseService.getAllCourses().stream()
                .filter(c -> c.getCourseID() == id)
                .findFirst()
                .orElse(null);
    }
    // --- INSTRUCTOR METHODS (CRUD) ---
    public List<Instructor> getAllInstructors() { return instructorService.getAllInstructors(); }
    public void saveInstructor(Instructor instructor) throws DuplicateIDException { instructorService.addInstructor(instructor); }
    public void updateInstructor(Instructor instructor) { instructorService.updateInstructor(instructor); }
    public void deleteInstructor(int id) { instructorService.removeInstructor(id); }
    public Instructor findInstructorById(int id) { return instructorService.getInstructorById(id); }

    // --- DEPARTMENT METHODS ---
    public List<Department> getAllDepartments() { return departmentService.getAllDepartments(); }
    public String saveDepartment(Department department) {
        departmentService.addDepartment(department);
        return "Department saved successfully.";
    }

    // --- SECTION METHODS ---
    public List<Section> getAllSections() { return sectionService.getAllSections(); }
    public String saveSection(Section section) {
        sectionService.addSection(section);
        return "Success: Section " + section.getSectionName() + " created in central registry!";
    }

    // --- ENROLLMENT METHODS ---
    public String enrollStudent(Student student, Section section) {
        try {
            enrollmentService.enrollStudentInSection(student, section);
            return "Success: " + student.getFullName() + " enrolled in " + section.getSectionName() + "!";
        } catch (SectionFullException e) {
            return e.getMessage();
        }
    }

    // --- TUITION METHODS ---
    public TuitionFeePayment getTuitionAccount(Student student) {
        return tuitionService.getOrCreateAccount(student);
    }
    public String assessTuition(TuitionFeePayment payment) {
        double total = tuitionService.calculateFee(payment);
        return "Tuition automatically assessed based on enrolled courses: Php " + total;
    }
    public String processPayment(TuitionFeePayment payment, double amount) {
        tuitionService.makePayment(payment, amount);
        double balance = tuitionService.getRemainingBalance(payment);
        return "Payment of Php " + amount + " successful. Remaining Balance: Php " + balance;
    }
    public double getBalance(TuitionFeePayment payment) {
        return tuitionService.getRemainingBalance(payment);
    }
}