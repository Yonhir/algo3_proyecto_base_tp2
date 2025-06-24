package edu.fiuba.algo3.modelo.cardcollections;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.errors.TheCardWasNotFound;

import java.util.ArrayList;
import java.util.List;

public abstract class CardCollection {
    protected List<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<>();
    }
    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void insertCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardCount() {
        return cards.size();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public void retrieveCard(Card card){
        if (!cards.remove(card)) {
            throw new TheCardWasNotFound("The card is not in the deck");
        }
    }
}
