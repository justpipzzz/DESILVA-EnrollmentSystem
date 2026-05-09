package org.example.service;

import org.example.model.Instructor;
import org.example.exception.DuplicateIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InstructorRegistrationTest {
    private InstructorRegistration service;

    @BeforeEach
    void setUp() {
        service = new InstructorRegistration();
    }

    @Test
    void testAddInstructor_Success() throws DuplicateIDException {
        Instructor i1 = new Instructor(1, "Smith", "John", "M", "N/A");
        service.addInstructor(i1);
        assertEquals(1, service.getAllInstructors().size());
    }

    @Test
    void testAddInstructor_DuplicateID_ThrowsException() {
        Instructor i1 = new Instructor(1, "Smith", "John", "M", "N/A");
        Instructor i2 = new Instructor(1, "Doe", "Jane", "A", "N/A"); // Same ID

        assertDoesNotThrow(() -> service.addInstructor(i1));
        assertThrows(DuplicateIDException.class, () -> service.addInstructor(i2));
    }

    @Test
    void testGetInstructorById() throws DuplicateIDException {
        Instructor i1 = new Instructor(2, "Doe", "Jane", "A", "N/A");
        service.addInstructor(i1);

        Instructor found = service.getInstructorById(2);
        assertNotNull(found);
        assertEquals("Doe", found.getLastName());
    }

    @Test
    void testRemoveInstructor() throws DuplicateIDException {
        Instructor i1 = new Instructor(1, "Smith", "John", "M", "N/A");
        service.addInstructor(i1);

        service.removeInstructor(1); // Service takes an int ID
        assertTrue(service.getAllInstructors().isEmpty());
    }
}