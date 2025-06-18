package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int health;
    private final DiscardPile discardPile;
    private final Hand hand;
    private final Deck deck;
    private final SpecialZone specialZone;
    private final CloseCombat closeCombat;
    private final Ranged ranged;
    private final Siege siege;

    public Player(String name, int health, Deck deck, SpecialZone specialZone, CloseCombat closeCombat, Ranged ranged, Siege siege) {
        this.name = name;
        this.health = health;
        discardPile = new DiscardPile();
        hand = new Hand(new ArrayList<>());
        this.deck = deck;
        this.specialZone = specialZone;
        this.closeCombat = closeCombat;
        this.ranged = ranged;
        this.siege = siege;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public Hand getHand(){
        return hand;
    }

    public int calculatePoints() {
        int points = closeCombat.calculatePoints() + ranged.calculatePoints() + siege.calculatePoints();
        return points;
    }

    public void playCard(Card card, Row row) {
        row.placeCard(card);
        hand.getCard(card);
    }
}
