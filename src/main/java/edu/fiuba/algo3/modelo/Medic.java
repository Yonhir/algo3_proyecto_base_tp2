package edu.fiuba.algo3.modelo;

import java.util.List;

public class Medic implements Modifier{
    private DiscardPile discardPile;
    private Player player;
    public Medic(DiscardPile discardPile, Player player){
        this.discardPile = discardPile;
        this.player = player;
    }
    @Override
    public void apply(Row row) {
        List<Card> unitCards = discardPile.getUnitCards();
        Card card;
        if (unitCards.size() == 1){
            card = unitCards.get(0);
        }else{
            card = player.selectCard(unitCards);
        }
        row.placeCard(card);
    }

}
