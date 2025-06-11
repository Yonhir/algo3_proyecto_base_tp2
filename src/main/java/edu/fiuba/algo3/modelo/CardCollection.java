package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class CardCollection {
    protected List<Card> cards;

    public CardCollection(List<Card> cards) {
        this.cards = cards;
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardCount() {
        return cards.size();
    }
}
