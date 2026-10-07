package org.mulungushi.studentservices.services;

import org.mulungushi.studentservices.dao.DatabaseManager;
import org.mulungushi.studentservices.models.Student;

public class StudentService {
    private DatabaseManager dbManager;

    public StudentService() {
        this.dbManager = new DatabaseManager();
    }

    public void registerNewStudent(String id, String name, String initialGrade) {
        // Business logic layer
        if(id == null || name == null) {
            throw new IllegalArgumentException("Invalid student data");
        }
        Student newStudent = new Student(id, name, initialGrade);
        dbManager.saveStudent(newStudent);
    }
}