package edu.fiuba.algo3;

import java.util.ArrayList;

public class Player {
    private final CardCollection cardsHand;
    private final CardCollection discardPile;
    private final Side side;
    private String name;
    private int health;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        cardsHand = new Hand();
        discardPile = new DiscardPile();
        side = new Side();
        cardsHand.addCard(new Unit("Arquero", "NA", 10, new Ranged()));
        cardsHand.addCard(new Unit("Paladino", "NA", 10, new CloseCombat()));
        cardsHand.addCard(new Unit("Catapulta", "NA", 10, new Siege()));
    }


    public void playCard(Card card, RowType type) {
        cardsHand.removeCard(card);
        side.placeCard(card, type);
    }

    public void cleanSide(){
        side.clearSide(discardPile);
    }

    public CardCollection getDiscardPile() {
        return discardPile;
    }
}
