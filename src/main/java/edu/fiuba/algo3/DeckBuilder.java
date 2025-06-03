package edu.fiuba.algo3;

import java.util.List;

public class DeckBuilder {
    private List<Card> cards;
    private List<Card> selectedCards;

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getSelection() {
        return selectedCards;
    }

    public void selectCard(Card card) {
        selectedCards.add(card);
    }

    public void addNSpecialCards(int cantidad) {

    }

    public void addNUnitCards(int cantidad) {

    }

    public Deck buildDeck() {
        Deck mazo = new Deck(this.cards);
        return mazo;
    }
}