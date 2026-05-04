package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<Program> programs; // Changed from Section to Program

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.programs = new ArrayList<>();
    }

    public void addProgram(Program program) { this.programs.add(program); }
    public String getDepartmentName() { return departmentName; }
    public List<Program> getPrograms() { return programs; }
}