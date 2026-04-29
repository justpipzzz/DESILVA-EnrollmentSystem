package org.example.service;

import org.example.model.Course;
import org.example.exception.DuplicateIDException;
import java.util.List;

public interface ICourseService {
    void addCourse(Course course) throws DuplicateIDException;
    void updateCourse(Course course);
    void removeCourse(Course course);
    List<Course> getAllCourses();
}