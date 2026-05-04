package org.example.service;

import org.example.model.Department;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRegistration implements IDepartmentService {
    private List<Department> departmentList = new ArrayList<>();

    @Override
    public void addDepartment(Department department) {
        departmentList.add(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentList;
    }
}