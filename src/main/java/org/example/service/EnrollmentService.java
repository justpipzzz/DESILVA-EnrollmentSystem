package org.example.service;

import org.example.model.Student;
import org.example.model.Section;
import org.example.exception.SectionFullException;

public class EnrollmentService implements IEnrollmentService {

    @Override
    public void enrollStudentInSection(Student student, Section section) throws SectionFullException {
        // Here is our advanced business logic validation!
        if (section.getEnrolledStudents().size() >= section.getMaxCapacity()) {
            throw new SectionFullException("Enrollment failed: Section " + section.getSectionName() + " is already at maximum capacity.");
        }

        // If it passes the check, enroll the student
        section.addStudent(student);
    }
}