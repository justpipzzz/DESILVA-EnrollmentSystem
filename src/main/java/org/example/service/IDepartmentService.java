package org.example.service;

import org.example.model.Department;
import java.util.List;

public interface IDepartmentService {
    void addDepartment(Department department);
    List<Department> getAllDepartments();
}