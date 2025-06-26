package edu.fiuba.algo3.modelo.cards;

import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.types.SectionType;
import edu.fiuba.algo3.modelo.colors.PlayerColor;

import java.util.List;

public abstract class Card {

    protected final String name;
    protected final String description;
    protected PlayerColor playerColor;

    protected final List<SectionType> sectionTypes;

    public Card(String name, String description, List<SectionType> sectionTypes) {
        this.name = name;
        this.description = description;
        this.sectionTypes = sectionTypes;
    }

    public abstract void play(Section section);

    public void verifySectionType(SectionType sectionType) {
        if (!haveSectionType(sectionType)) {
            throw new SectionTypeMismatchError("SectionType does not match for this card.");
        }
    }

    public abstract void setColor(PlayerColor playerColor);


    public boolean haveSectionType(SectionType sectionType) {
        return sectionTypes.stream().anyMatch(type -> type.getClass().equals(sectionType.getClass()));
    }


    public abstract void verifyColor(PlayerColor playerColor);
}
