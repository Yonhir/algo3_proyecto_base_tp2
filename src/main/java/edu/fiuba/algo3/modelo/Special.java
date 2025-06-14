package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Special extends Card {
    public Special(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }
}
