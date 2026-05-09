package org.example.service;

import org.example.model.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SectionRegistrationTest {
    private SectionRegistration service;

    @BeforeEach
    void setUp() {
        service = new SectionRegistration();
    }

    @Test
    void testAddAndGetAllSections() {
        Section sec1 = new Section("IT1A", 40);
        service.addSection(sec1);
        
        assertEquals(1, service.getAllSections().size());
        assertEquals("IT1A", service.getAllSections().get(0).getSectionName());
    }
}