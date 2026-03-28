package org.example.service;

import org.example.model.Course;
import org.example.model.Student;

import java.lang.invoke.StringConcatFactory;

public class CampusRegistrar {
    private final StudentReg STUDENT_REG;
    private final CourseReg COURSE_REG;

    public CampusRegistrar(StudentReg STUDENT_REG, CourseReg COURSE_REG) {
        this.STUDENT_REG = STUDENT_REG;
        this.COURSE_REG = COURSE_REG;
    }

    public String displayAllStudents(){
        STUDENT_REG.displayAllStudents();
        return "Displaying All Students...";
    }

    public String displayAllCourses(){
        COURSE_REG.displayAllCourses();
        return "Displaying All Courses...";
    }

    public String saveStudent(Student student){
        STUDENT_REG.saveStudent(student);
        return "Saving Student...";

    }

    public String saveCourse(Course course){
        COURSE_REG.saveCourse(course);
        return "Saving Course...";
    }

    public String deleteStudent(Student student){
        STUDENT_REG.deleteStudent(student);
        return "Deleting Student...";
    }

    public String deleteCourse(Course course){
        COURSE_REG.deleteCourse(course);
        return "Deleting Course...";

    }

    public String updateStudent(Student student){
        STUDENT_REG.updateStudent(student);
        return "Updating Student...";
    }

    public String updateCourse(Course course){
        COURSE_REG.updateCourse(course);
        return "Updating Course...";
    }


}
