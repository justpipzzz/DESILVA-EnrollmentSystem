package org.example.service;

import org.example.model.Course;
import org.example.model.Student;
import org.example.model.TuitionFeePayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TuitionServiceTest {
    private TuitionService service;

    @BeforeEach
    void setUp() {
        service = new TuitionService();
    }

    @Test
    void testGetOrCreateAccount() {
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        
        // 1. Creates new account
        TuitionFeePayment newAccount = service.getOrCreateAccount(s1);
        assertNotNull(newAccount);
        assertEquals(1001, newAccount.getStudent().getPersonID());
        
        // 2. Fetches existing account
        TuitionFeePayment fetchedAccount = service.getOrCreateAccount(s1);
        assertEquals(newAccount, fetchedAccount, "Should return the same instance");
    }

    @Test
    void testCalculateFee_WithCourses() {
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        Course c1 = new Course(1, "Math", 3);
        Course c2 = new Course(2, "Programming", 4);
        
        // Simulate enrollment
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);
        s1.addAllCourses(courses);

        TuitionFeePayment account = service.getOrCreateAccount(s1);
        
        // Total units = 7. RATE_PER_UNIT = 1500. Total = 10500.
        double fee = service.calculateFee(account);
        assertEquals(10500.0, fee);
        assertEquals(10500.0, account.getTotalTuitionFee());
    }

    @Test
    void testMakePaymentAndGetBalance() {
        Student s1 = new Student(1001, "Doe", "John", "M", "CITE", "BSIT", "1", "IT1A");
        TuitionFeePayment account = service.getOrCreateAccount(s1);
        account.setTotalTuitionFee(5000.0); // Manually set for test
        
        service.makePayment(account, 2000.0);
        
        assertEquals(2000.0, account.getAmountPaid());
        assertEquals(3000.0, service.getRemainingBalance(account));
    }
}