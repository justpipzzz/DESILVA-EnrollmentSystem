package org.example.service;

import org.example.model.Student;
import org.example.model.Section;
import org.example.model.Department;
import org.example.exception.SectionFullException;

public class EnrollmentService implements IEnrollmentService {

    @Override
    public void enrollStudentInSection(Student student, Section section) throws SectionFullException {
        // Here is our advanced business logic validation!
        if (section.getEnrolledStudents().size() >= section.getMaxCapacity()) {
            throw new SectionFullException("Enrollment failed: Section " + section.getSectionName() + " is already at maximum capacity (" + section.getMaxCapacity() + ").");
        }

        // If it passes the check, enroll the student
        section.getEnrolledStudents().add(student);
    }

    @Override
    public String viewDepartmentHierarchy(Department department) {
        StringBuilder builder = new StringBuilder();
        builder.append("Department: ").append(department.getDepartmentName()).append("\n");

        for (Section section : department.getSections()) {
            builder.append("  -> Section: ").append(section.getSectionName())
                    .append(" [Capacity: ").append(section.getEnrolledStudents().size())
                    .append("/").append(section.getMaxCapacity()).append("]\n");

            if (section.getAssignedInstructor() != null) {
                builder.append("     Instructor: ").append(section.getAssignedInstructor().getPersonName()).append("\n");
            }
            if (section.getCourse() != null) {
                builder.append("     Course: ").append(section.getCourse().getCourseName()).append("\n");
            }
        }
        return builder.toString();
    }
}