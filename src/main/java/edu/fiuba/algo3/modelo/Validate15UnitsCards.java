package edu.fiuba.algo3.modelo;

import java.util.List;

public class Validate15UnitsCards implements DeckValidator {
    private static final int MIN_UNITS = 15;
    private List<Card> cards;

    public Validate15UnitsCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean validate() {
        long unitsCount = cards.stream().filter(card -> card instanceof Unit).count();
        if (unitsCount < MIN_UNITS) {
            throw new NotEnoughUnitsCardsError("Instance of Deck without enough unit cards");
        }
        return true;
    }
}