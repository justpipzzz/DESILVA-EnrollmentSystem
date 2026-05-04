package org.example.service;

import org.example.exception.DuplicateIDException;
import org.example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegistrationTest {

    private StudentRegistration studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentRegistration();
    }

    @Test
    void testAddStudentSuccess() {
        Student s1 = new Student(101, "Alice", "BSIT");

        // Assert that adding a brand new student does NOT throw an error
        assertDoesNotThrow(() -> studentService.addStudent(s1));
        assertEquals(1, studentService.getAllStudents().size(), "List should have 1 student");
    }

    @Test
    void testDuplicateIdThrowsException() {
        Student s1 = new Student(101, "Alice", "BSIT");
        Student s2 = new Student(101, "Bob", "BSCS"); // Uh oh, same ID!

        // Add the first student successfully
        assertDoesNotThrow(() -> studentService.addStudent(s1));

        // Assert that adding the second student THROWS our custom exception
        assertThrows(DuplicateIDException.class, () -> {
            studentService.addStudent(s2);
        }, "Adding a duplicate ID should throw DuplicateIDException");
    }
}