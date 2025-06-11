package edu.fiuba.algo3.modelo;

public abstract class Card {
    protected String name;
    protected String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void play(Row row) {

    }

    public boolean canBePlaced(Row row) {
        return false;
    }
}