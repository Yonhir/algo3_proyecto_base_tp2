package edu.fiuba.algo3.models.cards;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.colors.Green;
import edu.fiuba.algo3.models.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.types.SectionType;
import edu.fiuba.algo3.models.colors.PlayerColor;

import java.util.List;

public abstract class Card extends Observable {

    protected final String name;
    protected final String description;
    protected PlayerColor playerColor;

    protected final List<SectionType> sectionTypes;

    public Card(String name, String description, List<SectionType> sectionTypes) {
        this.playerColor = new Green();
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
        return sectionTypes.stream().anyMatch(type -> type.getClass() == sectionType.getClass());
    }

    public boolean haveSameColor(PlayerColor color){
        return this.playerColor.equals(color);
    }

    public abstract void verifyColor(PlayerColor playerColor);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
