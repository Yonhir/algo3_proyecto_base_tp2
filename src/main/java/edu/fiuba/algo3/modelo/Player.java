package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int health;
    private final CardCollection hand;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        hand = new Hand(new ArrayList<>());
    }

    public Hand getHand(){ return (Hand) hand; }

}
