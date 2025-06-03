package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public abstract class CardCollection {
    private List<Card> cards;

    public CardCollection(List<Card> cards) {
        this.cards = cards;
    }

    public int getCardCount() {
        return this.cards.size();
    }
}