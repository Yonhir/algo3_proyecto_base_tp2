package edu.fiuba.algo3;


public abstract class Special extends Card {
    public Special(String name, String description) {
        super(name, description);
    }

    public void selectInDeckBuilder(DeckBuilder deckB) {
        deckB.selectSpecialCard(this);
    }

    public void unselectInDeckBuilder(DeckBuilder deckB) {
        deckB.unselectSpecialCard(this);
    }
}