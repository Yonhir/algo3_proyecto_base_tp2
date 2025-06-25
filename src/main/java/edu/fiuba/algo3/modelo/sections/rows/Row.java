package edu.fiuba.algo3.modelo.sections.rows;

import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ClearWeather;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.specials.weathers.Weather;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.types.SectionType;
import edu.fiuba.algo3.modelo.Colors.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Row implements Section {
    protected List<Card> cards = new ArrayList<>();
    protected Unit lastCard;
    protected Weather currentWeather;
    protected SectionType sectionType;
    protected Color color;

    protected Row(SectionType sectionType) {
        this.currentWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        this.sectionType = sectionType;
    }

    @Override
    public void placeCard(Card card, Round round) {
        card.verifySectionType(this.sectionType);
        card.play(this);
        round.playerPlayedCard();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        card.verifySectionType(this.sectionType);
        cards.add(card);
        currentWeather.apply(card, this);
        lastCard = (Unit) card;
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

    public Unit getLastCard() {
        return lastCard;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean sameColor(Color color) { return this.color.equals(color);}

    public boolean containsCard(Card card) {
        return this.cards.contains(card);
    }

    public int getCardCount() { return cards.size(); }
}
