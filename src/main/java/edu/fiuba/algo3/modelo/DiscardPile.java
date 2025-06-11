package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

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

    public Card getLastCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot get last card from empty discard pile");
        }
        return cards.get(cards.size() - 1);
    }
}
