package org.example.model;

import java.util.*;

public class Section {
    private String sectionName;
    private int maxCapacity;
    private Map<Course, Instructor> courseInstructors;
    private List<Student> enrolledStudents;

    public Section(String sectionName, int maxCapacity) {
        this.sectionName = sectionName;
        this.maxCapacity = maxCapacity;
        this.courseInstructors = new HashMap<>();
        this.enrolledStudents = new ArrayList<>();
    }

    // THE MISSING METHOD THAT FIXES YOUR ERROR:
    public void addStudent(Student student) {
        this.enrolledStudents.add(student);
    }

    public void assignCourseAndInstructor(Course course, Instructor instructor) {
        this.courseInstructors.put(course, instructor);
    }

    public String getSectionName() { return sectionName; }
    public int getMaxCapacity() { return maxCapacity; }
    public Map<Course, Instructor> getCourseInstructors() { return courseInstructors; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }

    // Helper for the "contains" logic used in Main.java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(sectionName, section.sectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionName);
    }
}