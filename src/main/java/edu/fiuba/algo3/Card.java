package edu.fiuba.algo3;

public abstract class Card {
    private String name;
    private String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void selectInDeckBuilder(DeckBuilder deckB);
}