package edu.fiuba.algo3.modelo;

public class Player {
    private String name;
    private int health;
    private Deck deck;
    private Hand hand;

    public Player(String name, int health, Deck deck, Hand hand){
        this.name = name;
        this.health = health;
        this.deck = deck;
        this.hand = hand;
    }

    public void getNCardsForHand(int n){
        hand.getNCardsFromDeck(deck,n);
    }
    public Deck getDeck(){
        return deck;
    }
    public Hand getHand(){
        return hand;
    }


}
