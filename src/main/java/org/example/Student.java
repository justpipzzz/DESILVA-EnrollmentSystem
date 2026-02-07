package org.example;

public class Student {
    public boolean getStudentID;
    private String studentID;
    private String studentName;
    private String program;

    public Student(){

    }

    public Student(String studentID, String studentName, String program){
        this.studentID = studentID;
        this.studentName = studentName;
        this.program = program;

    }

    public String getStudentID(){
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName(){
        return studentName;

    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
}
