package org.example.service;

import org.example.model.Student;
import org.example.exception.DuplicateIDException;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistration implements IStudentService {
    private List<Student> studentList = new ArrayList<>();

    @Override
    public void addStudent(Student student) throws DuplicateIDException {
        // Check for duplicates before adding
        for (Student s : studentList) {
            if (s.getPersonID() == student.getPersonID()) {
                throw new DuplicateIDException("Error: Student with ID " + student.getPersonID() + " already exists.");
            }
        }
        studentList.add(student);
    }

    @Override
    public void updateStudent(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getPersonID() == student.getPersonID()) {
                studentList.set(i, student);
                return;
            }
        }
    }

    @Override
    public void removeStudent(Student student) {
        // Using Java's built-in removeIf for cleaner code
        studentList.removeIf(s -> s.getPersonID() == student.getPersonID());
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }
}