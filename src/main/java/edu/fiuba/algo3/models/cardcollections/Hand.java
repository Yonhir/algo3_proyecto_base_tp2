package edu.fiuba.algo3.models.cardcollections;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.errors.InvalidCardAmountError;

import java.util.List;

public class Hand extends CardCollection{

    public void getNCardsFromDeck(Deck deck, int n){
        if(n <= 0){
            throw new InvalidCardAmountError("Invalid number of cards requested, must be greater than zero");
        }
        List<Card> cards = deck.retrieveNRandomCards(n);
        insertCards(cards);
    }

    public boolean containsCards(List<Card> cards) {
        return this.cards.containsAll(cards);
    }
}
