package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public abstract class CardCollection {
    protected List<Card> cards;

    public CardCollection(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card retrieveCard(Card card) {
        if (cards.remove(card)) {
            return card;
        }
        return null;
    }

    public int getCardCount() {
        return cards.size();
    }

    public void addCards(List<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
    }
}
