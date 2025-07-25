package edu.fiuba.algo3.models.sections.rows;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Scorch;
import edu.fiuba.algo3.models.cards.specials.weathers.ClearWeather;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.cards.specials.weathers.Weather;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.types.SectionType;
import edu.fiuba.algo3.models.colors.PlayerColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Row extends Observable implements Section {
    protected List<Card> cards = new ArrayList<>();
    protected Unit lastCard;
    protected Weather currentWeather;
    protected SectionType sectionType;
    protected PlayerColor playerColor;
    protected DiscardPile discardPile;

    protected Row(SectionType sectionType, DiscardPile discardPile) {
        this.currentWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        this.sectionType = sectionType;
        this.discardPile = discardPile;
    }

    @Override
    public void placeCard(Card card, Round round) {
        card.verifySectionType(this.sectionType);
        card.verifyColor(playerColor);
        card.play(this);
        round.playerPlayedCard();
    }

    public void placeCard(Card card) {
        card.verifySectionType(this.sectionType);
        card.play(this);
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
        List<Card> strongest = this.findAllCardsWithoutHeroModifierWithSamePoints(scorch);
        for (Card c : strongest) {
            scorch.burnStrongestCardFrom(c, this);
        }
    }

    public void findStrongestCardWithoutHeroModifier(Scorch scorch) {
        List<Card> cardsWithoutHeroModifier = cards.stream().filter(c -> !((Unit) c).hasHeroAsModifier()).collect(Collectors.toList());
        if (!cardsWithoutHeroModifier.isEmpty()) {
            Unit max = (Unit) cardsWithoutHeroModifier.get(0);
            for (Card card : cardsWithoutHeroModifier) {
                max = max.strongerThan((Unit) card);
            }
            scorch.saveStrongest(max);
        }
    }

    public List<Card> findAllCardsWithoutHeroModifierWithSamePoints(Scorch scorch) {
        List<Card> wanted = new ArrayList<>();
        List<Card> cardsWithoutHeroModifier = cards.stream().filter(c -> !((Unit) c).hasHeroAsModifier()).collect(Collectors.toList());
        for (Card card : cardsWithoutHeroModifier) {
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

    public void setColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public boolean haveSameSectionType(Card card) {
        return card.haveSectionType(sectionType);
    }

    public boolean containsCards(List<Card> cards){
        return this.cards.containsAll(cards);
    }

    public int getCardCount() {
        return cards.size();
    }

    public boolean haveSamePlayerColor(Card card) {
        return card.haveSameColor(playerColor);
    }
}
