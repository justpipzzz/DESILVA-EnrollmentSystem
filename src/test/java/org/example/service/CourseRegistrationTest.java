package org.example.service;

import org.example.model.Course;
import org.example.exception.DuplicateIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseRegistrationTest {
    private CourseRegistration service;

    @BeforeEach
    void setUp() {
        service = new CourseRegistration();
    }

    @Test
    void testAddCourse_Success() throws DuplicateIDException {
        Course c1 = new Course(101, "Java Programming", 3);
        service.addCourse(c1);
        assertEquals(1, service.getAllCourses().size());
        assertEquals("Java Programming", service.getAllCourses().get(0).getCourseName());
    }

    @Test
    void testAddCourse_DuplicateID_ThrowsException() {
        Course c1 = new Course(101, "Java Programming", 3);
        Course c2 = new Course(101, "Advanced Java", 4); // Same ID

        assertDoesNotThrow(() -> service.addCourse(c1));
        assertThrows(DuplicateIDException.class, () -> service.addCourse(c2));
    }

    @Test
    void testUpdateCourse() throws DuplicateIDException {
        Course c1 = new Course(101, "Java Programming", 3);
        service.addCourse(c1);

        Course updatedCourse = new Course(101, "Java Programming II", 4);
        service.updateCourse(updatedCourse);

        assertEquals("Java Programming II", service.getAllCourses().get(0).getCourseName());
        assertEquals(4, service.getAllCourses().get(0).getUnits());
    }

    @Test
    void testRemoveCourse() throws DuplicateIDException {
        Course c1 = new Course(101, "Java Programming", 3);
        service.addCourse(c1);

        service.removeCourse(c1);
        assertTrue(service.getAllCourses().isEmpty());
    }
}