package org.example.model;

public class Instructor{
    private String instructorID;
    private String instructorName;
    private String courses;

    public Instructor(String instructorID, String instructorName, String courses){
        this.instructorID = instructorID;
        this.instructorName = instructorName;
        this.courses = courses;
    }

    public String getInstructorID(){
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName(){
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setProgram(String program){

    }
}
