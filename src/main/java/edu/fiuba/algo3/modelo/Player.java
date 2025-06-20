package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.sections.*;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.rows.Siege;

public class Player {
    private final String name;
    private final DiscardPile discardPile;
    private final Hand hand;
    private final Deck deck;
    private final SpecialZone specialZone;
    private final CloseCombat closeCombat;
    private final Ranged ranged;
    private final Siege siege;
    private boolean hasPassed = false;
    private int roundsWon = 0;

    public Player(String name, Deck deck, SpecialZone specialZone, CloseCombat closeCombat, Ranged ranged, Siege siege) {
        this.name = name;
        discardPile = new DiscardPile();
        hand = new Hand();
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
    public void passRound() {
        this.hasPassed = true;
    }

    public boolean hasPassed() {
        return hasPassed;
    }

    public void resetPass() {
        this.hasPassed = false;
    }

    public void winRound() {
        this.roundsWon++;
    }

    public boolean hasWonGame() {
        return roundsWon >= 2;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void discardAllRows() {
        closeCombat.discardCards(discardPile);
        ranged.discardCards(discardPile);
        siege.discardCards(discardPile);
    }

    public boolean hasMoreOrEqualPointsThan(Player other) {
        return this.calculatePoints() >= other.calculatePoints();
    }

}
