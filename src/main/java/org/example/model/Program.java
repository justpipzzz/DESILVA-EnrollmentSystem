package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private String programName;
    private List<Section> sections;

    public Program(String programName) {
        this.programName = programName;
        this.sections = new ArrayList<>();
    }

    public void addSection(Section section) { this.sections.add(section); }
    public String getProgramName() { return programName; }
    public List<Section> getSections() { return sections; }
}