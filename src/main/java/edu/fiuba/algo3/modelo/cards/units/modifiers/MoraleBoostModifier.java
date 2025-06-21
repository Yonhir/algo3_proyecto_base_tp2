package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.rows.Row;

import java.util.List;

public class MoraleBoostModifier implements Modifier{
    @Override
    public void apply(Row row) {
        Unit thisCard = row.getLastCard();
        List<Card> cards = row.getCards();
        for (Card card : cards) {
            int points = ((Unit) card).calculatePoints();
            if(!thisCard.equals(card)) ((Unit) card).setPoints(points + 1);
        }
    }

}
