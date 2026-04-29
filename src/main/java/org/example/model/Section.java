package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String sectionName;
    private int maxCapacity;
    private Course course;
    private Instructor assignedInstructor;
    private List<Student> enrolledStudents;

    public Section(String sectionName, int maxCapacity, Course course) {
        this.sectionName = sectionName;
        this.maxCapacity = maxCapacity;
        this.course = course;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }

    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Instructor getAssignedInstructor() { return assignedInstructor; }
    public void setAssignedInstructor(Instructor assignedInstructor) { this.assignedInstructor = assignedInstructor; }

    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(List<Student> enrolledStudents) { this.enrolledStudents = enrolledStudents; }
}