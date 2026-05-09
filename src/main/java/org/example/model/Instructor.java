package org.example.model;

public class Instructor extends Person {
    // assignedDepartment removed as requested

    public Instructor(int personID, String lastName, String firstName, String middleName, String s) {
        super(personID, lastName, firstName, middleName);
    }
}