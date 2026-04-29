package org.example.service;

import org.example.model.Student;
import org.example.exception.DuplicateIDException;
import java.util.List;

public interface IStudentService {
    void addStudent(Student student) throws DuplicateIDException;
    void updateStudent(Student student);
    void removeStudent(Student student);
    List<Student> getAllStudents();
}