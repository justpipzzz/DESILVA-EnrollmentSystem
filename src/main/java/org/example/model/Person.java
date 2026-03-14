package org.example.model;

public abstract class Person {
    private int PersonID;
    private String PersonName;

    public Person(int PersonID){
        this.PersonID = PersonID;

    }

    public Person(int PersonID, String PersonName){
        this.PersonID = PersonID;
        this.PersonName = PersonName;

    }

    public int getPersonID() {
        return PersonID;
    }
    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;

    }

    public String getPersonName() {
        return PersonName;

    }

    public void setPersonName(String PersonName) {
        this.PersonName = PersonName;
    }

    public abstract void mainTask(String work);
}
