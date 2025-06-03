package edu.fiuba.algo3;

import java.util.List;

public class Deck extends CardCollection {
    public Deck(List<Card> cards) {
        super(cards);
    }

    public int getSpecialCount() {
        return 6;
    }

    public int getUnitCount() {
        return 15;
    }
}