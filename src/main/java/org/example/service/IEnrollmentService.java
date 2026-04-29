package org.example.service;

import org.example.model.Student;
import org.example.model.Section;
import org.example.model.Department;
import org.example.exception.SectionFullException;

public interface IEnrollmentService {
    // This is where our custom exception comes into play!
    void enrollStudentInSection(Student student, Section section) throws SectionFullException;

    // Returns a formatted string of the hierarchy so the service doesn't print directly
    String viewDepartmentHierarchy(Department department);
}