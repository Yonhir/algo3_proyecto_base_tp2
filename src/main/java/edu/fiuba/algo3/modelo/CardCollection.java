package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class CardCollection {
    protected List<Card> cards;

    public CardCollection(List<Card> cards) {
        this.cards = cards;
    }
}
