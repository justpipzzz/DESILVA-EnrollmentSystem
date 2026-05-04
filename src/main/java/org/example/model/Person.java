package org.example.model;

public abstract class Person {
    protected int personID;
    protected String lastName;
    protected String firstName;
    protected String middleName;

    public Person(int personID, String lastName, String firstName, String middleName) {
        this.personID = personID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public String getFullName() {
        String mi = (middleName == null || middleName.trim().isEmpty()) ? "" : middleName.trim().charAt(0) + ".";
        return lastName + ", " + firstName + " " + mi;
    }

    public int getPersonID() { return personID; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
}