package edu.fiuba.algo3;

import java.util.List;

public class DeckBuilder {
    private List<Card> cards;

    public void addNSpecialCards(int cantidad) {
    }

    public void addNUnitCards(int cantidad) {
    }

    public Deck buildDeck() {
        return new Deck(this.cards);
    }
}