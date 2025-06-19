package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile extends CardCollection {

    public DiscardPile() {
        super(new ArrayList<>());
    }

    @Override
    public void addCard(Card card) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
        super.addCard(card);
    }

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            addCard(card);
        }

    }

    public Card getLastCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot get last card from empty discard pile");
        }
        return cards.get(cards.size() - 1);
    }

    public Card getLastUnitCards() {
        for (int i = cards.size() - 1; i >= 0; i--) {
            if (cards.get(i) instanceof Unit) {
                return cards.get(i);
            }
        }
        throw new IllegalStateException("No unit cards");
    }
}
