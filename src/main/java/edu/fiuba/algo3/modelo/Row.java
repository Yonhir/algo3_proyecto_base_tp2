package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Row implements CardTarget {
    protected List<Card> cards = new ArrayList<>();
    protected Weather currentWeather;

    @Override
    public void placeCard(Card card) {
        if (!card.canBePlaced(this)) {
            throw new IllegalArgumentException("La carta no puede colocarse en esta fila.");
        }
        card.play(this);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean canBePlacedIn(Unit unit) {
        return false;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        if (currentWeather != null) {
            currentWeather.apply(card, this);
        }
    }

    public void addWeather(Weather weather) {
        this.currentWeather = weather;
        for (Card card : cards) {
            currentWeather.apply(card, this);
        }
    }

    public int calculatePoints() {
        int total = 0;
        for (Card card : cards) {
            if (card instanceof Unit) {
                total += ((Unit) card).calculatePoints();
            }
        }
        return total;
    }

    public void discardCards(DiscardPile discardPile) {
        discardPile.addCards(cards);
        cards.clear();
    }
}
