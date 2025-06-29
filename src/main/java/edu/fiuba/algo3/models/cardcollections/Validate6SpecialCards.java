package edu.fiuba.algo3.models.cardcollections;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.errors.NotEnoughSpecialsCardsError;

import java.util.List;

public class Validate6SpecialCards implements DeckValidator {
    private static final int MIN_SPECIALS = 6;

    @Override
    public void validate(List<Card> cards) {
        long specialsCount =  cards.stream().filter(card -> card instanceof Special).count();
        if (specialsCount < MIN_SPECIALS) {
            throw new NotEnoughSpecialsCardsError("Instance of Deck without enough special cards");
        }
    }
}