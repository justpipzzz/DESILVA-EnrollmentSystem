package org.example.service;

import org.example.model.Section;
import java.util.ArrayList;
import java.util.List;

public class SectionRegistration implements ISectionService {
    private List<Section> sectionList = new ArrayList<>();

    @Override
    public void addSection(Section section) {
        sectionList.add(section);
    }

    @Override
    public List<Section> getAllSections() {
        return sectionList;
    }
}