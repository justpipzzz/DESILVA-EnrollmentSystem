package org.example.service;

import org.example.model.Course;
import org.example.exception.DuplicateIDException;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistration implements ICourseService {
    private List<Course> courseList = new ArrayList<>();

    @Override
    public void addCourse(Course course) throws DuplicateIDException {
        for (Course c : courseList) {
            if (c.getCourseID() == course.getCourseID()) {
                throw new DuplicateIDException("Error: Course with ID " + course.getCourseID() + " already exists.");
            }
        }
        courseList.add(course);
    }

    @Override
    public void updateCourse(Course course) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID() == course.getCourseID()) {
                courseList.set(i, course);
                return;
            }
        }
    }

    @Override
    public void removeCourse(Course course) {
        courseList.removeIf(c -> c.getCourseID() == course.getCourseID());
    }

    @Override
    public List<Course> getAllCourses() {
        return courseList;
    }
}