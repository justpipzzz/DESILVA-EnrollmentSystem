package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Section {
    private String sectionName;
    private int maxCapacity;
    // HAS-A: Maps a specific Course to a specific Instructor!
    private Map<Course, Instructor> courseInstructors;
    private List<Student> enrolledStudents;

    public Section(String sectionName, int maxCapacity) {
        this.sectionName = sectionName;
        this.maxCapacity = maxCapacity;
        this.courseInstructors = new HashMap<>();
        this.enrolledStudents = new ArrayList<>();
    }

    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }

    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public Map<Course, Instructor> getCourseInstructors() { return courseInstructors; }

    // New method to assign a course and its instructor at the same time
    public void assignCourseAndInstructor(Course course, Instructor instructor) {
        this.courseInstructors.put(course, instructor);
    }

    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public void addStudent(Student student) { this.enrolledStudents.add(student); }
}