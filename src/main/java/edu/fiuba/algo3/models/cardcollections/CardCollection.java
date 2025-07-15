package edu.fiuba.algo3.models.cardcollections;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.errors.TheCardWasNotFound;

import java.util.ArrayList;
import java.util.List;

public abstract class CardCollection extends Observable {
    protected List<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
        notifyObservers();
    }

    public void insertCards(List<Card> cards) {
        this.cards.addAll(cards);
        notifyObservers();
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
        notifyObservers();
    }
    public boolean containsCard(Card card) {
        return this.cards.contains(card);
    }
}
