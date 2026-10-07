package org.mulungushi.studentservices.models;

public class Student {
    private String studentId;
    private String name;
    private String grade;

    public Student(String studentId, String name, String grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}