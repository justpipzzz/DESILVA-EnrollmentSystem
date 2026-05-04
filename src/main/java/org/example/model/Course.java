package org.example.model;

public class Course {
    private int courseID;
    private String courseName;
    private int units;

    public Course() {}

    public Course(int courseID, String courseName, int units) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.units = units;
    }

    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) { this.courseID = courseID; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }
}