package edu.fiuba.algo3.models.cardcollections;

import edu.fiuba.algo3.models.cards.Card;

import java.util.List;

interface DeckValidator {
    void validate(List<Card> cards);
}