package edu.fiuba.algo3.modelo;

import java.util.List;

public class Deck extends CardCollection {
    List<Card> units;
    List<Card> specials;

    public Deck(List<Card> cards, List<Card> units, List<Card> specials) {
        super(cards);
        this.units = units;
        this.specials = specials;
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