package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.colors.Green;
import edu.fiuba.algo3.modelo.colors.PlayerColor;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.sections.*;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.rows.Siege;

import java.util.List;

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

    private void setColor(PlayerColor playerColor, Green bothPlayers) {
        setColorToCards(playerColor, bothPlayers);
        setColorToRows(playerColor);
    }

    private void setColorToRows(PlayerColor playerColor) {
        closeCombat.setColor(playerColor);
        ranged.setColor(playerColor);
        siege.setColor(playerColor);
    }

    private void setColorToCards(PlayerColor playerColor, Green bothPlayers) {
        deck.setColorToCards(playerColor, bothPlayers);
    }

    public Player(String name, int health, Deck deck, SpecialZone specialZone, CloseCombat closeCombat, Ranged ranged, Siege siege, PlayerColor playerColor, Green bothPlayers) {
        this.name = name;
        this.health = health;
        discardPile = new DiscardPile();
        hand = new Hand();
        this.deck = deck;
        this.specialZone = specialZone;
        this.closeCombat = closeCombat;
        this.ranged = ranged;
        this.siege = siege;
        setColor(playerColor, bothPlayers);
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

    public void playCard(Card card, Row row) {
        row.placeCard(card);
        hand.getCard(card);
    }
}
