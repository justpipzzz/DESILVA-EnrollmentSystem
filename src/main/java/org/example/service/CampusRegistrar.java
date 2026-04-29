package org.example.service;

import org.example.model.Course;
import org.example.model.Student;
import org.example.exception.DuplicateIDException;
import java.util.List;

public class CampusRegistrar {
    private final IStudentService studentService;
    private final ICourseService courseService;

    // Now uses the new Interfaces via Dependency Injection
    public CampusRegistrar(IStudentService studentService, ICourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // Instead of printing directly, we now return the List of data
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    public String saveStudent(Student student) {
        try {
            studentService.addStudent(student);
            return "Success: Student saved!";
        } catch (DuplicateIDException e) {
            // This catches our custom exception and returns the error message!
            return e.getMessage();
        }
    }

    public String saveCourse(Course course) {
        try {
            courseService.addCourse(course);
            return "Success: Course saved!";
        } catch (DuplicateIDException e) {
            return e.getMessage();
        }
    }

    public String deleteStudent(Student student) {
        studentService.removeStudent(student);
        return "Deleting Student...";
    }

    public String deleteCourse(Course course) {
        courseService.removeCourse(course);
        return "Deleting Course...";
    }

    public String updateStudent(Student student) {
        studentService.updateStudent(student);
        return "Updating Student...";
    }

    public String updateCourse(Course course) {
        courseService.updateCourse(course);
        return "Updating Course...";
    }
}