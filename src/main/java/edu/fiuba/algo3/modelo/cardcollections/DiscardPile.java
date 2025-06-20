package edu.fiuba.algo3.modelo.cardcollections;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;

import java.util.List;

public class DiscardPile extends CardCollection {

    @Override
    public void addCard(Card card) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
        super.addCard(card);
    }

    @Override
    public void insertCards(List<Card> cards) {
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
