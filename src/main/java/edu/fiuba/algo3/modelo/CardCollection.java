package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class CardCollection {
    private final List<Card> cards;

    public CardCollection(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() { return cards; }

}
