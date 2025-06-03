package edu.fiuba.algo3;

public class Player {
    private String name;
    private int health;
    private Deck deck;

    public Player(String name, int health, Deck deck) {
        this.name = name;
        this.health = health;
        this.deck = deck;
    }
}