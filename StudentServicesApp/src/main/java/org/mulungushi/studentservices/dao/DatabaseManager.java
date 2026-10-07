package org.mulungushi.studentservices.dao;

import org.mulungushi.studentservices.models.Student;
import java.util.ArrayList;
import java.util.List;

// Simulates JDBC/JPA Database interactions
public class DatabaseManager {
    private List<Student> mockDatabase = new ArrayList<>();

    public void saveStudent(Student student) {
        mockDatabase.add(student);
        System.out.println("Saved to database: " + student.getName());
    }

    public Student findStudentById(String id) {
        return mockDatabase.stream()
            .filter(s -> s.getStudentId().equals(id))
            .findFirst()
            .orElse(null);
    }
}