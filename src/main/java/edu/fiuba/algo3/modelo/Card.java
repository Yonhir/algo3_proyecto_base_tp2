package edu.fiuba.algo3.modelo;

public abstract class Card {

    protected final String name;
    protected final String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }
}