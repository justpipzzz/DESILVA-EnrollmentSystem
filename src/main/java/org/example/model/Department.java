package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<Program> programs; // HAS-A relationship with Program

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.programs = new ArrayList<>();
    }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public List<Program> getPrograms() { return programs; }
    public void addProgram(Program program) { this.programs.add(program); }
}