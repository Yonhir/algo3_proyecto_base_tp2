package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Row {
    protected List<Card> cards = new ArrayList<>();

    public void placeCard(Card card) {
        if (!card.canBePlaced(this)) {
            throw new IllegalArgumentException("La carta no puede colocarse en esta fila.");
        }
        card.play(this);
    }

    public abstract boolean canBePlacedIn(Unit unit);

    public void addCard(Card card) {
        cards.add(card);
    }

    public void discardCards(DiscardPile discardPile) {
        discardPile.addCards(cards);
        cards.clear();
    }
}
