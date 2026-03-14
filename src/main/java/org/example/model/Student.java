package org.example.model;

public class Student extends Person {
    private String program;

    public Student(int StudentID){
        super(StudentID);
    }

    @Override
    public void mainTask(String work) {
        System.out.println(work);
    }

    public Student(int StudentID, String StudentName, String program){
        super(StudentID, StudentName);
        this.program = program;
    }

    public String getProgram(){
        return program;
    }

    public void setProgram(String program){
        this.program = program;
    }

    public void display(){
        System.out.println("Student ID: " + getPersonID());
        System.out.println("Student Name: " + getPersonName());
        System.out.println("Program: " + getProgram());
    }
}
