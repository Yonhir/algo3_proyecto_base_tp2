package edu.fiuba.algo3;

import java.util.List;

public class Deck extends CardCollection {
    private int specials;
    private int units;
    public Deck(List<Card> cards) {
        super(cards);
    }

    public int getSpecialCount() {
        return specials;
    }

    public int getUnitCount() {
        return units;
    }
}