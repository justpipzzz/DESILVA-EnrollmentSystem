package org.example.service;

import org.example.model.Student;
import java.util.ArrayList;

public class StudentRegistration {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void saveStudent(Student student){
        studentList.add(student);
    }

    public void displayAllStudents() {
        for (Student s : studentList) {
            System.out.println("\nStudentID: " + s.getStudentID() + "\nStudent Name: " + s.getStudentName() + "\nProgram: " + s.getProgram());

        }
    }

    public void updateStudent(Student student){
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID() == student.getStudentID()) {
                studentList.set(i, student);

                break;
            }
        }
    }

    public void deleteStudent(Student student){
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID() == student.getStudentID()) {
                studentList.remove(i);

                break;
            }
        }

    }
}
