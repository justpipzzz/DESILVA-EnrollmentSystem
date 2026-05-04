package org.example.model;

public class Course {
    private int courseID;
    private String courseName;
    private String program;
    private int units;

    public Course() {}

    public Course(int courseID, String courseName, String program, int units) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.program = program;
        this.units = units;
    }

    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) { this.courseID = courseID; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }
}