package edu.fiuba.algo3.modelo;

import java.util.List;

public class Validate15UnitsCards implements DeckValidator {
    private static final int MIN_UNITS = 15;

    @Override
    public void validate(List<Card> cards) {
        long unitsCount = cards.stream().filter(card -> card instanceof Unit).count();
        if (unitsCount < MIN_UNITS) {
            throw new NotEnoughUnitsCardsError("Instance of Deck without enough unit cards");
        }
    }
}
