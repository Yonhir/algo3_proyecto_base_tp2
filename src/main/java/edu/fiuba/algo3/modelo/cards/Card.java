package edu.fiuba.algo3.modelo.cards;

import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.SectionType;

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

    public void play(Section section) {
    }

    public void verifySectionType(SectionType sectionType) {
        boolean matches = sectionTypes.stream().anyMatch(type -> type.getClass().equals(sectionType.getClass()));
        if (!matches) {
            throw new SectionTypeMismatchError("SectionType does not match for this card.");
        }
    }
}
