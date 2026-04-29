package org.example.service;

import org.example.model.Course;
import org.example.model.Student;
import org.example.model.Instructor;
import org.example.exception.DuplicateIDException;
import java.util.List;

public class CampusRegistrar {
    private final IStudentService studentService;
    private final ICourseService courseService;
    private final IInstructorService instructorService; // NEW

    // Updated Constructor
    public CampusRegistrar(IStudentService studentService, ICourseService courseService, IInstructorService instructorService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.instructorService = instructorService; // NEW
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
    public String deleteStudent(Student student) {
        studentService.removeStudent(student);
        return "Deleting Student...";
    }
    public String updateStudent(Student student) {
        studentService.updateStudent(student);
        return "Updating Student...";
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
    public String deleteCourse(Course course) {
        courseService.removeCourse(course);
        return "Deleting Course...";
    }
    public String updateCourse(Course course) {
        courseService.updateCourse(course);
        return "Updating Course...";
    }

    // --- INSTRUCTOR METHODS (NEW) ---
    public List<Instructor> getAllInstructors() { return instructorService.getAllInstructors(); }

    public String saveInstructor(Instructor instructor) {
        try {
            instructorService.addInstructor(instructor);
            return "Success: Instructor saved!";
        } catch (DuplicateIDException e) {
            return e.getMessage();
        }
    }
}