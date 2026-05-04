package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String department;
    private String program;
    private String yearLevel;
    private String sectionName;
    private List<Course> enrolledCourses;

    public Student(int personID, String lastName, String firstName, String middleName,
                   String department, String program, String yearLevel, String sectionName) {
        super(personID, lastName, firstName, middleName);
        this.department = department;
        this.program = program;
        this.yearLevel = yearLevel;
        this.sectionName = sectionName;
        this.enrolledCourses = new ArrayList<>();
    }

    public void addAllCourses(List<Course> courses) {
        this.enrolledCourses.addAll(courses);
    }

    public String getDepartment() { return department; }
    public String getProgram() { return program; }
    public String getSectionName() { return sectionName; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public String getYearLevel() { return yearLevel; }
}