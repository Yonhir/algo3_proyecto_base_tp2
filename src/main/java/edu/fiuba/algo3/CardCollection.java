package edu.fiuba.algo3;

import java.util.List;

public abstract class CardCollection {
    private List<Card> cards;

    public CardCollection(List<Card> cards) {
        this.cards = cards;
    }

    public int getCardCount() {
        return 21;
    }
}