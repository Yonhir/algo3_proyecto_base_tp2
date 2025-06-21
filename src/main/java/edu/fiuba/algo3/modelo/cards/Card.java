package edu.fiuba.algo3.modelo.cards;

import edu.fiuba.algo3.modelo.colors.Green;
import edu.fiuba.algo3.modelo.errors.SectionPlayerMismatchError;
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
        boolean matches = sectionTypes.stream().anyMatch(type -> type.getClass().equals(sectionType.getClass()));
        if (!matches) {
            throw new SectionTypeMismatchError("SectionType does not match for this card.");
        }
    }

    public abstract void setColor(PlayerColor playerColor, Green bothPlayers);

    public void setColor(PlayerColor playerColor){
        this.playerColor = playerColor;
    }

    public boolean sameColor(PlayerColor playerColor) {
        return (playerColor.equals(this.playerColor) || this.playerColor instanceof Green);
    }

    public void verifyColor(PlayerColor playerColor) {
        boolean sameColor = playerColor.equals(this.playerColor);
        boolean cardColorGreen =  this.playerColor instanceof Green;
        if (!(sameColor || cardColorGreen)) throw new SectionPlayerMismatchError("Side does not match for this card.");
    }
}
