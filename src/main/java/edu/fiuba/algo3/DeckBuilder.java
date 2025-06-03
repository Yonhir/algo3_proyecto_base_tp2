package edu.fiuba.algo3;

import java.util.List;
import java.util.ArrayList;

public class DeckBuilder {
    private List<Card> cards;
    private List<Card> selectedCards;

    public DeckBuilder() {
        this.cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
    }
    public Deck buildDeck() {
        return new Deck(this.selectedCards);
    }

    public void selectCard(Card card) {
        selectedCards.add(card);
    }

    public List<Card> getSelection() {
        return selectedCards;
    }
}