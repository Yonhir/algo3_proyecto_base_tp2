package edu.fiuba.algo3.models.turnManagement;

import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.rows.Siege;

public class Player {
    private final String name;
    private final DiscardPile discardPile;
    private final Hand hand;
    private final Deck deck;
    private final CloseCombat closeCombat;
    private final Ranged ranged;
    private final Siege siege;
    private int roundsWon = 0;

    private void setColor(PlayerColor playerColor) {
        setColorToCards(playerColor);
        setColorToRows(playerColor);
        setColorDiscardPile(playerColor);
    }

    private void setColorToRows(PlayerColor playerColor) {
        closeCombat.setColor(playerColor);
        ranged.setColor(playerColor);
        siege.setColor(playerColor);
    }

    private void setColorToCards(PlayerColor playerColor) {
        deck.setColorToCards(playerColor);
    }
    
    private void setColorDiscardPile(PlayerColor color){
        discardPile.setColor(color);
    }

    public Player(String name, Deck deck, DiscardPile discardPile, CloseCombat closeCombat, Ranged ranged, Siege siege, PlayerColor playerColor) {
        this.name = name;
        this.discardPile = discardPile;
        hand = new Hand();
        this.deck = deck;
        this.closeCombat = closeCombat;
        this.ranged = ranged;
        this.siege = siege;

        setColor(playerColor);
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
        closeCombat.discardCards();
        ranged.discardCards();
        siege.discardCards();
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

    public Player chooseWinnerAgainst(Player other) {
        if (this.hasWonGame()) {
            return this;
        }
        return other;
    }
}
