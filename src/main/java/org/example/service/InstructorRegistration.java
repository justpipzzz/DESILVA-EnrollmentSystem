package org.example.service;

import org.example.model.Instructor;
import org.example.exception.DuplicateIDException;
import java.util.ArrayList;
import java.util.List;

public class InstructorRegistration implements IInstructorService {
    private List<Instructor> instructorList = new ArrayList<>();

    @Override
    public void addInstructor(Instructor instructor) throws DuplicateIDException {
        for (Instructor i : instructorList) {
            if (i.getPersonID() == instructor.getPersonID()) {
                throw new DuplicateIDException("Error: Instructor ID " + instructor.getPersonID() + " exists.");
            }
        }
        instructorList.add(instructor);
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        for (int i = 0; i < instructorList.size(); i++) {
            if (instructorList.get(i).getPersonID() == instructor.getPersonID()) {
                instructorList.set(i, instructor);
                return;
            }
        }
    }

    @Override
    public void removeInstructor(int instructorID) {
        instructorList.removeIf(i -> i.getPersonID() == instructorID);
    }

    @Override
    public Instructor getInstructorById(int id) {
        return instructorList.stream()
                .filter(i -> i.getPersonID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorList;
    }
}