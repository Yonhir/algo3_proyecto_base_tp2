package edu.fiuba.algo3;

import java.util.List;

public class Player implements SpecialEffectApplicable {
    private final String name;
    private int health;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final Deck deck;
    private final Side side;
    private boolean confirmedHand;

    public Player(String name, int health, Hand hand, DiscardPile discardPile, Deck deck, Side side) {
        this.name = name;
        this.health = health;
        this.hand = hand;
        this.discardPile = discardPile;
        this.deck = deck;
        this.side = side;
        this.confirmedHand = false;
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public void drawNCards(int n) {
        if (!confirmedHand) {
            List<Card> cardsToDraw = deck.retrieveNRandomCard(n);
            hand.addCards(cardsToDraw);
        }
    }

    public void drawAndDiscard(Card card) {
        if (!confirmedHand) {
            Card cardToDiscard = hand.retrieveCard(card);
            discardPile.addCard(cardToDiscard);
            Card cardToDraw = deck.retrieveRandomCard();
            hand.addCard(cardToDraw);
        }
    }

    public void confirmHand() {
        confirmedHand = true;
    }

    public void playCard(Card card, RowType selectedRowType) {
        Card cardToPlay = hand.retrieveCard(card);
        if (cardToPlay == null) {
            throw new IllegalArgumentException("Card not found in hand");
        }
        side.placeCard(cardToPlay, selectedRowType);
    }

    public int calculatePoints() {
        return side.calculateTotalPoints();
    }

    public void clearBoard() {
        side.clearSide(discardPile);
    }

    public void loseHealth() {
        health--;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOwnerPlayer(this, selectedRowType);
        side.applyEffectOnOwner(specialEffect, selectedRowType);
        hand.applyEffectOnOwner(specialEffect, selectedRowType);
        deck.applyEffectOnOwner(specialEffect, selectedRowType);
        discardPile.applyEffectOnOwner(specialEffect, selectedRowType);
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOpponentPlayer(this, selectedRowType);
        side.applyEffectOnOpponent(specialEffect, selectedRowType);
        hand.applyEffectOnOpponent(specialEffect, selectedRowType);
        deck.applyEffectOnOpponent(specialEffect, selectedRowType);
        discardPile.applyEffectOnOpponent(specialEffect, selectedRowType);
    }
}
