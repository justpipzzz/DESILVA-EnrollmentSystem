package org.example.service;

import org.example.model.Course;

public interface CourseReg {
    void saveCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Course course);
    void displayAllCourses();
}
