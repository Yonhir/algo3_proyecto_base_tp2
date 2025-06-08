package edu.fiuba.algo3.modelo;

public abstract class Card {
    protected String name;
    protected String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void play(Row row);

    public abstract boolean puedeSerColocadaEn(Row row);
}
