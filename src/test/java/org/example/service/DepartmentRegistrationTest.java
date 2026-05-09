package org.example.service;

import org.example.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentRegistrationTest {
    private DepartmentRegistration service;

    @BeforeEach
    void setUp() {
        service = new DepartmentRegistration();
    }

    @Test
    void testAddAndGetAllDepartments() {
        Department d1 = new Department("CITE");
        service.addDepartment(d1);
        
        assertEquals(1, service.getAllDepartments().size());
        assertEquals("CITE", service.getAllDepartments().get(0).getDepartmentName());
    }
}