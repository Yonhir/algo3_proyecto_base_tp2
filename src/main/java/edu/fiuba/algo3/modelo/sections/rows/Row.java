package edu.fiuba.algo3.modelo.sections.rows;

import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.Scorch;
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
    protected DiscardPile discardPile;

    protected Row(SectionType sectionType, DiscardPile discardPile) {
        this.currentWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        this.sectionType = sectionType;
        this.discardPile = discardPile;
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

    public void applyScorch(Scorch scorch) {
        List<Card> strongest = this.findAllWithSamePoints(scorch);
        for (Card c : strongest) {
            scorch.burnStrongestCardFrom(c, this);
        }
    }

    public void findStrongestCard(Scorch scorch) {
        if(!cards.isEmpty()) {
            Unit max = (Unit) cards.get(0);
            for (Card card : cards) {
                max = max.strongerThan((Unit) card);
            }
            scorch.saveStrongest(max);
        }
    }

    public List<Card> findAllWithSamePoints(Scorch scorch) {
        List<Card> wanted = new ArrayList<>();
        for (Card card : cards) {
            if (scorch.matchesStrongest((Unit) card)) {
                wanted.add(card);
            }
        }
        return wanted;
    }

    public void discardCard(Card card) {
        discardPile.addCard(card);
        cards.remove(card);
    }

    public boolean containsCard(Card card) {
        return this.cards.contains(card);
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

    public void discardCards() {
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
}
