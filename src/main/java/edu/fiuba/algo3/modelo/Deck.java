package edu.fiuba.algo3.modelo;

import java.util.Arrays;
import java.util.List;

public class Deck extends CardCollection {
    private List<DeckValidator> validators;

    public Deck(List<Card> cards) {
        super(cards);
        this.validators = Arrays.asList(
                new Validate6SpecialCards(cards),
                new Validate15UnitsCards(cards)
        );
        validate();
    }

    private void validate() {
        for (DeckValidator deckV : this.validators) {
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