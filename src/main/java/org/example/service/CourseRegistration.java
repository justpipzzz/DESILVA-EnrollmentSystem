package org.example.service;

import org.example.model.Course;
import java.util.ArrayList;

public class CourseRegistration {
    private ArrayList<Course> courseList =  new ArrayList<>();

    public void saveCourse(Course course){
        courseList.add(course);
    }

    public void displayAllCourses(){
        for (Course course : courseList){
            System.out.println("Course ID: " + course.getCourseID() + " Course Name: " + course.getCourseName() + " Program: " + course.getProgram() + "\n");
        }
    }

    public void updateCourse(Course course){
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID() == course.getCourseID()) {
                courseList.set(i, course);
                System.out.println("Course Update Successful!");
                break;
            }
        }
    }

    public void deleteCourse(Course course){
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID() == course.getCourseID()) {
                courseList.remove(i);
                System.out.println("Course Delete Successful!");
            }
            break;
        }
    }
}
