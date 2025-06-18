package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscardPile extends CardCollection {

    @Override
    public void addCard(Card card) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
        super.addCard(card);
    }

    public void addCards(List<Card> cards) {
        for (Card c : cards) {
            addCard(c);
        }
    }

    public Card getLastCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot get last card from empty discard pile");
        }
        int lastCard = (cards.size() - 1);
        return cards.remove(lastCard);
    }
}
