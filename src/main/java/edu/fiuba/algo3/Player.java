package edu.fiuba.algo3;

public class Player {
    private String name;
    private int health;
    private Hand hand;
    private Deck deck;
    private DiscardPile discardPile;
    private Side side;

    public Player(String name, int health, Deck deck) {
        this.name = name;
        this.health = health;
        this.deck = deck;
    }
}