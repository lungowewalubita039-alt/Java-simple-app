package org.mulungushi.studentservices.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void testStudentRegistrationValidation() {
        StudentService service = new StudentService();
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.registerNewStudent(null, null, "A");
        });
        
        assertEquals("Invalid student data", exception.getMessage());
    }
}