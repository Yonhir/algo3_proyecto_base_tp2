package edu.fiuba.algo3.modelo;

import java.util.List;

public class TightBond implements Modifier {
    @Override
    public void apply(Row row) {
        List<Card> cards = row.getCards();
        int tightBondCount = 0;
        
        // Count how many cards have the TightBond modifier
        for (Card card : cards) {
            if (card instanceof Unit && ((Unit) card).haveModifier(this)) {
                tightBondCount++;
            }
        }

        // If we have multiple cards with TightBond, multiply their points
        if (tightBondCount > 1) {
            for (Card card : cards) {
                if (card instanceof Unit && ((Unit) card).haveModifier(this)) {
                    Unit unit = (Unit) card;
                    unit.setPoints(unit.calculatePoints() * tightBondCount);
                }
            }
        }
    }
}
