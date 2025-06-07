package edu.fiuba.algo3.modelo;

import java.util.List;

public class Deck {
    List<Card> units;
    List<Card> specials;

    public Deck(List<Card> units, List<Card> specials) {
        this.units = units;
        this.specials = specials;
    }

    public int getCardCount() {
        return 21;
    }

    public int getUnitsCount() {
        return 15;
    }

    public int getSpecialsCount() {
        return 6;
    }
}