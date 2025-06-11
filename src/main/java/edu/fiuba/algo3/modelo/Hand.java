package edu.fiuba.algo3.modelo;

import java.util.List;

public class Hand extends CardCollection{
    public Hand(List<Card> cards) {
        super(cards);
    }

    public void getNCardsFromDeck(Deck deck, int n){
        if(deck.isEmpty() || deck.getCardCount() < n){
            throw new NotEnoughtCardsInDeckError("Deck without enough cards");
        }
        List<Card> cards = deck.retrieveNRandomCards(n);
        this.cards.addAll(cards);
    }
}
