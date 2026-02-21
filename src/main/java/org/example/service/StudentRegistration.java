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
            System.out.println(s.getStudentID() + " " + s.getStudentName() + " " + s.getProgram());

        }
    }

}
