package org.example.model;

public class Student extends Person {
    private String program;

    public Student(int studentID) {
        super(studentID);
    }

    public Student(int studentID, String studentName, String program) {
        super(studentID, studentName);
        this.program = program;
    }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
}