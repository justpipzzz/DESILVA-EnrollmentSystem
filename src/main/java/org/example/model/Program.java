package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private String programName; // e.g., BSIT, BSCS
    private List<Section> sections; // HAS-A relationship with Section

    public Program(String programName) {
        this.programName = programName;
        this.sections = new ArrayList<>();
    }

    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }

    public List<Section> getSections() { return sections; }
    public void addSection(Section section) { this.sections.add(section); }
}