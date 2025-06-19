package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.Row;

import java.util.List;

public class MoraleBoostModifier implements Modifier{
    /*
    private Row[] rows;
    public MoraleBoostModifier(Row... rows) {
        this.rows = rows;
    }

    @Override
    public void apply(Row row) {
        if(Arrays.stream(rows).noneMatch(row1 -> row1.equals(row))) throw new CardPlayInOpponentPlaceException("Morale Boost can only be played in your own rows.");
        Unit thisCard = row.getLastCard();
        List<Card> cards = row.getCards();
        for (Card card : cards) {
            int points = ((Unit) card).calculatePoints();
            if(!thisCard.equals(card)) ((Unit) card).setPoints(points + 1);
        }
    }
    */

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
