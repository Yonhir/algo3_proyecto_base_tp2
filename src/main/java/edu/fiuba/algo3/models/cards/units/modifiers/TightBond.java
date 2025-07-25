package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.rows.Row;

import java.util.ArrayList;
import java.util.List;

public class TightBond implements Modifier {
    @Override
    public void apply(Row row) {
        List<Card> cards = row.getCards();
        List<Unit> tightBondUnits = new ArrayList<>();
        int newCardPoints = Integer.MAX_VALUE;
        
        // Get all the units with the TightBond modifier
        for (Card card : cards) {
            if (card instanceof Unit && ((Unit) card).haveModifier(this)) {
                tightBondUnits.add((Unit) card);
            }
        }

        // Get the points of the last card (the new one being placed)
        for (Unit unit : tightBondUnits) {
            if (unit.calculatePoints() < newCardPoints) {
                newCardPoints = unit.calculatePoints();
            }
        }

        // If we have multiple cards with TightBond, multiply their points
        if (tightBondUnits.size() > 1) {
            for (Unit unit : tightBondUnits) {
                unit.setPoints(newCardPoints * tightBondUnits.size());
            }
        }
    }
    
    @Override
    public String getDescription() {
        return "Vínculo Fuerte: Multiplica los puntos por el número de cartas con el mismo nombre";
    }
}
