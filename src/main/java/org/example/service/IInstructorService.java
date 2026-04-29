package org.example.service;

import org.example.model.Instructor;
import org.example.model.Section;
import org.example.exception.DuplicateIDException;
import java.util.List;

public interface IInstructorService {
    void addInstructor(Instructor instructor) throws DuplicateIDException;
    void assignInstructorToSection(Instructor instructor, Section section);
    String getInstructorDetails(Instructor instructor);
    List<Instructor> getAllInstructors();
}