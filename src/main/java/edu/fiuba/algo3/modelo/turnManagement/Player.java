package edu.fiuba.algo3.modelo.turnManagement;

import edu.fiuba.algo3.modelo.Colors.Color;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.rows.Siege;


import java.util.List;

public class Player {
    private final String name;
    private final DiscardPile discardPile;
    private final Hand hand;
    private final Deck deck;
    private final CloseCombat closeCombat;
    private final Ranged ranged;
    private final Siege siege;
    private int roundsWon = 0;

    public Player(String name, Deck deck, CloseCombat closeCombat, Ranged ranged, Siege siege, Color color) {

        this.name = name;
        discardPile = new DiscardPile();
        hand = new Hand();
        this.deck = deck;
        this.closeCombat = closeCombat;
        this.ranged = ranged;
        this.siege = siege;
        setColor(color);
    }
  
    private void setColorToCards(Color color) {
        List<Card> cards = deck.getCards();
        for (Card card : cards) {
            card.setColor(color);
        }
    }
  
    private void setColorToRows(Color color) {
        closeCombat.setColor(color);
        ranged.setColor(color);
        siege.setColor(color);
    }
  
    private void setColor(Color color) {
        setColorToCards(color);
        setColorToRows(color);
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public Hand getHand(){
        return hand;
    }

    public int calculatePoints() {
        return closeCombat.calculatePoints() + ranged.calculatePoints() + siege.calculatePoints();
    }


    public void playCard(Card card, Row row, Round round) {
        row.placeCard(card, round);
        hand.retrieveCard(card);
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

    public void assignRoundVictoryToBetterPlayer(Player other) {
        int myPoints = this.calculatePoints();
        int otherPoints = other.calculatePoints();

        if (myPoints > otherPoints) {
            this.winRound();
        } else if (myPoints < otherPoints) {
            other.winRound();
        }
    }


    public Player getWinnerAgainst(Player other) {
        if (this.hasWonGame()) {
            return this;
        }
        return other;
    }
}
