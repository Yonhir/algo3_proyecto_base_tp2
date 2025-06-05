package edu.fiuba.algo3;

import java.util.List;

public class Player {
    private String nombre;
    private int health;
    private Deck deck;
    private Hand hand;
    private DiscardPile dicardPile;

    public Player(String nombre, int health,Deck deck,Hand hand ,DiscardPile dicardPile){
        this.nombre = nombre;
        this.health = health;
        this.hand = hand;
        this.deck = deck;
        this.dicardPile = dicardPile;
    }

    public List<Card> getNCardsFromDeck(int n) {
        return deck.getNRandomCard(n);
    }

    public void putCardsInHand(List<Card> cards){
        for(Card card: cards){
            card.selectInHand(hand);
        }
    }
    public CardCollection getHand() {
        return hand;
    }

}
