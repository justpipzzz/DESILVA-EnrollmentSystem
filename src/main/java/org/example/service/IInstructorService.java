package org.example.service;

import org.example.model.Instructor;
import org.example.exception.DuplicateIDException;
import java.util.List;

public interface IInstructorService {
    void addInstructor(Instructor instructor) throws DuplicateIDException;
    void updateInstructor(Instructor instructor); // Added for CRUD
    void removeInstructor(int instructorID);     // Added for CRUD
    Instructor getInstructorById(int id);         // Added for CRUD
    List<Instructor> getAllInstructors();
}