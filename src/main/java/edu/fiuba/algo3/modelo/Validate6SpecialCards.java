package edu.fiuba.algo3.modelo;

import java.util.List;

public class Validate6SpecialCards implements DeckValidator {
    private static final int MIN_SPECIALS = 6;
    private List<Card> cards;

    public Validate6SpecialCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean validate() {
        return cards.stream().filter(card -> card instanceof Special).count() >= MIN_SPECIALS;
    }
}