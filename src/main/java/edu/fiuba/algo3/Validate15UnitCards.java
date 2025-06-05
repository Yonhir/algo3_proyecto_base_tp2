package edu.fiuba.algo3;

import java.util.List;

public class Validate15UnitCards implements DeckValidator {
    private List<Card> cards;
    public static final int MIN_UNIT = 15;

    public Validate15UnitCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean validate() {
        return this.cards.size() >= MIN_UNIT;
    }
}
