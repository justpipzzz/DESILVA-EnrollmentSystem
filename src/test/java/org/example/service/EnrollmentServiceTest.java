package org.example.service;

import org.example.model.Section;
import org.example.model.Student;
import org.example.exception.SectionFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceTest {
    private EnrollmentService service;

    @BeforeEach
    void setUp() {
        service = new EnrollmentService();
    }

    @Test
    void testEnrollStudent_Success() throws SectionFullException {
        // Create a section with a capacity of 40
        Section section = new Section("IT1A", 40);
        Student student = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");

        // Enroll the student
        assertDoesNotThrow(() -> service.enrollStudentInSection(student, section));

        // Verify the student was added to the section's list
        assertEquals(1, section.getEnrolledStudents().size());
        assertEquals(student, section.getEnrolledStudents().get(0));
    }

    @Test
    void testEnrollStudent_SectionFull_ThrowsException() {
        // Create a section with a maximum capacity of 1
        Section section = new Section("IT1A", 1);
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        Student s2 = new Student(1002, "Smith", "Jane", "A", "CITE", "BSIT", "1", "IT1A");

        // Enroll the first student (should succeed)
        assertDoesNotThrow(() -> service.enrollStudentInSection(s1, section));

        // Attempt to enroll the second student (should throw SectionFullException)
        SectionFullException thrown = assertThrows(
                SectionFullException.class, 
                () -> service.enrollStudentInSection(s2, section)
        );

        // Optional: Verify the exception message is correct
        assertTrue(thrown.getMessage().contains("is already at maximum capacity"));
        
        // Verify the section still only has 1 student
        assertEquals(1, section.getEnrolledStudents().size());
    }
}