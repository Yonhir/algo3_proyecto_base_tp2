package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.errors.NotUnitCardError;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.sections.rows.Row;

public class Medic implements Modifier{
    private DiscardPile discardPile;

    public Medic(DiscardPile discardPile){
        this.discardPile = discardPile;
    }
    @Override
    public void apply(Row row) {
        try {
            Card card = discardPile.getLastUnitCardFromType(row);
            row.placeCard(card);
        }
        catch (NotUnitCardError e){
           // System.out.println(e.getMessage());
        }
    }
}
