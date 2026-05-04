package org.example.model;

import java.util.ArrayList;
import java.util.List;

// CHILD CLASS
public class Student extends Person {
    private String department;
    private String program;
    private String yearLevel;
    private String sectionName;
    private List<Course> enrolledCourses;

    public Student(int personID, String lastName, String firstName, String middleName,
                   String department, String program, String yearLevel, String sectionName) {
        super(personID, lastName, firstName, middleName); // Calls the Person constructor
        this.department = department;
        this.program = program;
        this.yearLevel = yearLevel;
        this.sectionName = sectionName;
        this.enrolledCourses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        this.enrolledCourses.add(course);
    }

    public String getDepartment() { return department; }
    public String getProgram() { return program; }
    public String getYearLevel() { return yearLevel; }
    public String getSectionName() { return sectionName; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
}