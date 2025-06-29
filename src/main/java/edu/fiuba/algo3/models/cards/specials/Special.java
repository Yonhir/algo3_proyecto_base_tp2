package edu.fiuba.algo3.models.cards.specials;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.models.sections.types.SectionType;

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
        if(this.playerColor == null) throw new SectionPlayerMismatchError("Side does not match for this card.");
    }
}
