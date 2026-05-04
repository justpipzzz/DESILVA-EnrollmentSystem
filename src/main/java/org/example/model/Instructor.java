package org.example.model;

// CHILD CLASS
public class Instructor extends Person {
    private String assignedDepartment;

    public Instructor(int personID, String lastName, String firstName, String middleName, String assignedDepartment) {
        super(personID, lastName, firstName, middleName); // Calls the Person constructor
        this.assignedDepartment = assignedDepartment;
    }

    public String getAssignedDepartment() { return assignedDepartment; }
    public void setAssignedDepartment(String assignedDepartment) { this.assignedDepartment = assignedDepartment; }
}