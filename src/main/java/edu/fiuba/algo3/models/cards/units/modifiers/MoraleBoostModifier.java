package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.rows.Row;

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
    
    @Override
    public String getDescription() {
        return "Impulso de Moral: Aumenta en 1 punto a todas las demás unidades de la fila";
    }
}
