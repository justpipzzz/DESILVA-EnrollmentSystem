package org.example.service;

import org.example.model.Student;
import org.example.exception.DuplicateIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentRegistrationTest {
    private StudentRegistration service;

    @BeforeEach
    void setUp() {
        service = new StudentRegistration();
    }

    @Test
    void testAddStudent_Success() throws DuplicateIDException {
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        service.addStudent(s1);
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    void testAddStudent_DuplicateID_ThrowsException() {
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        Student s2 = new Student(1001, "Smith", "Jane", "A", "CITE", "BSIT", "1", "IT1A");
        
        assertDoesNotThrow(() -> service.addStudent(s1));
        assertThrows(DuplicateIDException.class, () -> service.addStudent(s2));
    }

    @Test
    void testUpdateStudent() throws DuplicateIDException {
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        service.addStudent(s1);

        Student updatedStudent = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "2", "IT2A");
        service.updateStudent(updatedStudent);

        assertEquals("2", service.getAllStudents().get(0).getYearLevel());
        assertEquals("IT2A", service.getAllStudents().get(0).getSectionName());
    }

    @Test
    void testRemoveStudent() throws DuplicateIDException {
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        service.addStudent(s1);
        
        service.removeStudent(s1);
        assertTrue(service.getAllStudents().isEmpty());
    }
}