package edu.fiuba.algo3.modelo;

public abstract class Card {
    protected String name;
    protected String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void play(CardTarget target) {

    }

    public void addToSpecialZone(SpecialZone specialZone) {
    }

    public void deleteFromRow(int points, Row row) {

    }

    public int compareTo(int points) {
        return 0;
    }
}