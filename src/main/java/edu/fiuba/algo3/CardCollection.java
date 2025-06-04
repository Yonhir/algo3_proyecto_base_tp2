package edu.fiuba.algo3;

import java.util.ArrayList;

public class CardCollection {

    private final ArrayList<Card> cards;

    public CardCollection() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void addCards(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }
}