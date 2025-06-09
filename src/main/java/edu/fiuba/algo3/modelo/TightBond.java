package edu.fiuba.algo3.modelo;

import java.util.List;

public class TightBond implements Modifier {
    @Override
    public void apply(Row row) {
        List<Card> cards = row.getCards();
        int tightBondCount = 0;
        int newCardPoints = 0;
        
        // Count how many cards have the TightBond modifier and get the points of the new card
        for (Card card : cards) {
            if (card instanceof Unit && ((Unit) card).haveModifier(this)) {
                tightBondCount++;
                // Get the points of the last card (the new one being placed)
                if (tightBondCount == 1) {
                    newCardPoints = ((Unit) card).calculatePoints();
                }
                else if (tightBondCount > 1 && newCardPoints > ((Unit) card).calculatePoints()) {
                    newCardPoints = ((Unit) card).calculatePoints();
                }
            }
        }

        // If we have multiple cards with TightBond, multiply their points
        if (tightBondCount > 1) {
            for (Card card : cards) {
                if (card instanceof Unit && ((Unit) card).haveModifier(this)) {
                    Unit unit = (Unit) card;
                    unit.setPoints(newCardPoints * tightBondCount);
                }
            }
        }
    }
}
