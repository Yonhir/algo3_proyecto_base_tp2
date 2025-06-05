package edu.fiuba.algo3;

import java.util.List;

public class Validate6SpecialCards implements DeckValidator {
    private List<Card> cards;
    public static final int MIN_SPECIAL = 6;

    public Validate6SpecialCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean validate() {
        return this.cards.size() >= MIN_SPECIAL;
    }
}
