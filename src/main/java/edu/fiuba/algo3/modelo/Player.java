package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int health;
    private final CardCollection discardPile;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        discardPile = new DiscardPile(new ArrayList<>());
    }

    public DiscardPile getDiscardPile() { return (DiscardPile) discardPile; }

}