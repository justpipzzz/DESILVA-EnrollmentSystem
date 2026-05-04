package org.example.service;

import org.example.model.Student;
import org.example.model.Section;
import org.example.exception.SectionFullException;

public interface IEnrollmentService {
    void enrollStudentInSection(Student student, Section section) throws SectionFullException;
}