package org.example.service;

import org.example.model.Student;
import java.util.ArrayList;

public class StudentRegistration implements StudentReg {
    private ArrayList<Student> studentList = new ArrayList<>();

    @Override
    public void saveStudent(Student student){
        studentList.add(student);
    }

    @Override
    public void displayAllStudents() {
        for (Student s : studentList) {
            System.out.println("\nStudentID: " + s.getPersonID() + "\nStudent Name: " + s.getPersonName() + "\nProgram: " + s.getProgram());

        }
    }

    @Override
    public void updateStudent(Student student){
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getPersonID() == student.getPersonID()) {
                studentList.set(i, student);

                break;
            }
        }
    }

    @Override
    public void deleteStudent(Student student){
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getPersonID() == student.getPersonID()) {
                studentList.remove(i);

                break;
            }
        }

    }
}
