package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.errors.NotUnitCardError;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.sections.rows.Row;

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
        }
    }
    
    @Override
    public String getDescription() {
        return "Médico: Revive una carta de unidad del cementerio";
    }
}
