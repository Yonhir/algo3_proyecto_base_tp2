package edu.fiuba.algo3.modelo.cardcollections;

import edu.fiuba.algo3.modelo.cards.Card;

import java.util.List;

interface DeckValidator {
    void validate(List<Card> cards);
}