package org.example.service;

import org.example.model.Instructor;
import org.example.exception.DuplicateIDException;
import java.util.List;

public interface IInstructorService {
    void addInstructor(Instructor instructor) throws DuplicateIDException;
    String getInstructorDetails(Instructor instructor);
    List<Instructor> getAllInstructors();
}