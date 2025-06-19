package edu.fiuba.algo3.modelo;

import java.util.List;

public class Hand extends CardCollection{

    public void getNCardsFromDeck(Deck deck, int n){
        if(n <= 0){
            throw new InvalidCardAmountError("Invalid number of cards requested, must be greater than zero");
        }
        List<Card> cards = deck.retrieveNRandomCards(n);
        insertCards(cards);
    }
}
