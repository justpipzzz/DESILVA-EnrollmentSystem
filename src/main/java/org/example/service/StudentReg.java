package org.example.service;

import org.example.model.Student;

public interface StudentReg {
    void saveStudent(Student student);
    void displayAllStudents();
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
