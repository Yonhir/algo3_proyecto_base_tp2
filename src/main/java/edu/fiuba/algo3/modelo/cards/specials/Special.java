package edu.fiuba.algo3.modelo.cards.specials;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.colors.Blue;
import edu.fiuba.algo3.modelo.colors.PlayerColor;
import edu.fiuba.algo3.modelo.colors.Red;
import edu.fiuba.algo3.modelo.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.modelo.sections.types.SectionType;

import java.util.List;

public abstract class Special extends Card {
    public Special(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }

    @Override
    public void setColor(PlayerColor playerColor){
        this.playerColor = playerColor;
    }

    @Override
    public void verifyColor(PlayerColor playerColor) {
        boolean colorRed = playerColor.equals(new Red());
        boolean colorBlue = playerColor.equals(new Blue());
        if(!(colorRed || colorBlue)) throw new SectionPlayerMismatchError("Side does not match for this card.");
    }
}
