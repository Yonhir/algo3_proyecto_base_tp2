package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Row {
    protected List<Card> cards = new ArrayList<>();

    public void placeCard(Card card) {
        validateCard(card);
        addCard(card);
    }

    protected void validateCard(Card card) {
        if (!card.puedeSerColocadaEn(this)) {
            throw new IllegalArgumentException("La carta no puede colocarse en esta fila.");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public abstract boolean puedeColocarUnidad(Unit unit);

    public int calculatePoints() {
        int totalPoints = 0;
        for (Card card : cards) {
            if (card instanceof Unit) {
                totalPoints += ((Unit) card).calculatePoints();
            }
        }
        return totalPoints;
    }

    public void discardCards(DiscardPile discardPile) {
        for (Card card : cards) {
            discardPile.addCard(card);
        }
        cards.clear();
    }
}
