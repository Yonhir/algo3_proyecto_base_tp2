package edu.fiuba.algo3.modelo.cards;

import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.types.SectionType;
import edu.fiuba.algo3.modelo.colors.Color;

import java.util.List;

public abstract class Card {

    protected final String name;
    protected final String description;
    protected Color color;
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

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean sameColor(Color color) {
        return color.equals(this.color);
    }

    public String getName() {
        return name;
    }
}
