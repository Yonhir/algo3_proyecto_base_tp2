package edu.fiuba.algo3.modelo;

import java.util.List;

public class Medic implements Modifier{
    private DiscardPile discardPile;

    public Medic(DiscardPile discardPile){
        this.discardPile = discardPile;
    }
    @Override
    public void apply(Row row) {
        Card card = discardPile.getLastUnitCards();
        row.placeCard(card);
    }

}
