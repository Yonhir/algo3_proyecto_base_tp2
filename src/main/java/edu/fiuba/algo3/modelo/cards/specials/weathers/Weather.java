package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.colors.Green;
import edu.fiuba.algo3.modelo.colors.PlayerColor;
import edu.fiuba.algo3.modelo.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.types.SectionType;

import java.util.List;

public abstract class Weather extends Special {
    public Weather(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }

    @Override
    public void setColor(PlayerColor playerColor, Green bothPlayers){
        this.setColor(bothPlayers);
    }

    @Override
    public void verifyColor(PlayerColor playerColor) {
        boolean cardColorGreen =  this.playerColor instanceof Green;
        if (!cardColorGreen) throw new SectionPlayerMismatchError("Side does not match for this card.");

    }

    public abstract void apply(Card card, Row row);
}
