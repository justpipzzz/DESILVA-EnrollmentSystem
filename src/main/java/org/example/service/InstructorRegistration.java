package org.example.service;

import org.example.model.Instructor;
import org.example.model.Section;
import org.example.exception.DuplicateIDException;
import java.util.ArrayList;
import java.util.List;

public class InstructorRegistration implements IInstructorService {
    private List<Instructor> instructorList = new ArrayList<>();

    @Override
    public void addInstructor(Instructor instructor) throws DuplicateIDException {
        // Check for duplicates
        for (Instructor i : instructorList) {
            if (i.getPersonID() == instructor.getPersonID()) {
                throw new DuplicateIDException("Error: Instructor with ID " + instructor.getPersonID() + " already exists.");
            }
        }
        instructorList.add(instructor);
    }

    @Override
    public void assignInstructorToSection(Instructor instructor, Section section) {
        section.setAssignedInstructor(instructor);
    }

    @Override
    public String getInstructorDetails(Instructor instructor) {
        return "Instructor ID: " + instructor.getPersonID() + " | Name: " + instructor.getPersonName();
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorList;
    }
}