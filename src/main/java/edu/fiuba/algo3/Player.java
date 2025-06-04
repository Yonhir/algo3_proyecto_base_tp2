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
    }


    public void playCard(Card card, RowType type) {
        cardsHand.removeCard(card);
        side.placeCard(card, type);
    }

    public int calculatePoints() {
        return side.calculatedPoints();
    }

    public void putCardsInDiscardPile(ArrayList<Card> cards) {
        discardPile.addCards(cards);
    }

    public void cleanSide(){
        ArrayList<Card> cards_to_clean = side.clean();
        putCardsInDiscardPile(cards_to_clean);
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile.getCards();
    }
}
