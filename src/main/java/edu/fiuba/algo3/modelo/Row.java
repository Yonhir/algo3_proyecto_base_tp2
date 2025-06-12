package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Row implements CardTarget {
    protected List<Card> cards = new ArrayList<>();

    public void placeCard(Card card) {
        card.play(this);
    }

    public List<Card> getPlayedCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int mostPowerfulCard() {
        int max = 0;
        for (Card card : this.cards) {
            int actual = card.compareTo(max);
            max = actual;
        }
        return max;
    }

    public void deleteCardsWithPoints(int mostPowerful) {
        List<Card> copia = new ArrayList<>(this.cards);
        for (Card card : copia) {
            card.deleteFromRow(mostPowerful, this);
        }
    }

    public void deleteCard(Card card) {
        cards.remove(card);
    }
}