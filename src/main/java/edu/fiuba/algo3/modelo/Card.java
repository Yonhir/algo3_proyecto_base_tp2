package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Card {

    protected final String name;
    protected final String description;
    protected final List<SectionType> sectionTypes;

    public Card(String name, String description, List<SectionType> sectionTypes) {
        this.name = name;
        this.description = description;
        this.sectionTypes = sectionTypes;
    }

    public void play(Section target) {
    }

    public void verifySectionType(SectionType sectionType) {
        boolean matches = sectionTypes.stream().anyMatch(type -> type.getClass().equals(sectionType.getClass()));
        if (!matches) {
            throw new SectionTypeMismatchError("SectionType does not match for this card.");
        }
    }
}
