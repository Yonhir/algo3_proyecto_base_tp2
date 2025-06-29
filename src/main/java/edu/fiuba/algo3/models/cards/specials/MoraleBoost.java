package edu.fiuba.algo3.models.cards.specials;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.types.SectionType;

import java.util.List;

public class MoraleBoost extends Special {

    public MoraleBoost(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }

    @Override
    public void verifyColor(PlayerColor playerColor) {
        boolean sameColor = playerColor.equals(this.playerColor);
        if (!sameColor) throw new SectionPlayerMismatchError("Side does not match for this card.");

    }

    @Override
    public void play(Section section) {
        Row row = (Row) section;
        List<Card> cards= row.getCards();
        for (Card card : cards) {
            int points = ((Unit) card).calculatePoints();
            ((Unit) card).setPoints(points * 2);
        }
    }
}
