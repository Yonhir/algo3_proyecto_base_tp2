package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Row implements Section {
    protected List<Card> cards = new ArrayList<>();
    protected Weather currentWeather;
    protected SectionType sectionType;

    protected Row(SectionType sectionType) {
        this.currentWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        this.sectionType = sectionType;
    }

    @Override
    public void placeCard(Card card) {
        card.verifySectionType(this.sectionType);
        card.play(this);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
        currentWeather.apply(card, this);
    }

    public void applyWeather(Weather weather) {
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
        discardPile.insertCards(cards);
        cards.clear();
    }
}
