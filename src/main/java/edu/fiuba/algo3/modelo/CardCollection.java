package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class CardCollection {
    protected List<Card> cards;

    public CardCollection(List<Card> cards) {
        this.cards = cards;
    }
  
    public int getCardCount() {
        return cards.size();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public Card retrieveCard(Card card){
        if (cards.contains(card)) {
            cards.remove(card);
            return card;
        }
        throw new TheCardWasNotFound("The card is not in the deck");

    }
}
