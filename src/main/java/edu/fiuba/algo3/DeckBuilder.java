package edu.fiuba.algo3;

import java.util.List;
import java.util.ArrayList;

public class DeckBuilder {
    public static final int MIN_UNITS = 15;
    public static final int MIN_SPECIALS = 6;

    private List<Card> cards;
    private List<Card> selectedCards;
    private List<Card> units;
    private List<Card> specials;

    public DeckBuilder() {
        this.cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        this.units = new ArrayList<>();
        this.specials = new ArrayList<>();
    }

    public Deck buildDeck() {
        if (this.units.size() < MIN_UNITS || this.specials.size() < MIN_SPECIALS) {
            throw new IllegalArgumentException("Not Enough Cards");
        }
        return new Deck(this.units, this.specials);
    }

    public void selectUnitCard(Card card) {
        units.add(card);
    }

    public void selectSpecialCard(Card card) {
        specials.add(card);
    }

    public void selectCard(Card card) {
        selectedCards.add(card);
        card.selectInDeckBuilder(this);
    }

    public List<Card> getSelection() {
        return selectedCards;
    }

    public List<Card> getSpecialsSelected() {
        return specials;
    }

    public List<Card> getUnitsSelected() {
        return units;
    }
}