package edu.fiuba.algo3.modelo;

import java.util.List;

public class Deck extends CardCollection {
    private static final int MIN_UNITS = 15;
    private static final int MIN_SPECIALS = 6;
    private List<Card> units;
    private List<Card> specials;

    public Deck(List<Card> cards, List<Card> units, List<Card> specials) {
        super(cards);
        this.units = units;
        this.specials = specials;
        validate();
    }

    private void validate() {
        if ((units.size() < MIN_UNITS) || (specials.size() < MIN_SPECIALS)) {
            throw new IllegalArgumentException();
        }
    }
    public int getCardCount() {
        return cards.size();
    }

    public int getUnitsCount() {
        return units.size();
    }

    public int getSpecialsCount() {
        return specials.size();
    }
}