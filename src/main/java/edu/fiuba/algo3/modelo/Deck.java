package edu.fiuba.algo3.modelo;

import java.util.Arrays;
import java.util.List;

public class Deck extends CardCollection {
    public Deck(List<Card> cards) {
        super(cards);
        List<DeckValidator> validators = Arrays.asList(
                new Validate6SpecialCards(cards),
                new Validate15UnitsCards(cards)
        );
        validate(validators);
    }

    private void validate(List<DeckValidator> validators) {
        for (DeckValidator deckV : validators) {
            if (!deckV.validate()) {
                throw new IllegalArgumentException("Deck inválido");
            }
        }
    }

    public int getCardCount() {
        return cards.size();
    }

    public long getUnitsCount() {
        return cards.stream().filter(card -> card instanceof Unit).count();
    }

    public long getSpecialsCount() {
        return cards.stream().filter(card -> card instanceof Special).count();
    }
}