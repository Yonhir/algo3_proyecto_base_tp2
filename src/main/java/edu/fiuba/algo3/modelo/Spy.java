package edu.fiuba.algo3.modelo;

public class Spy implements Modifier {
    private Deck deck;
    private Row row;
    private Hand hand;
    public Spy(Deck deck, Hand hand, Row row) {
        this.deck = deck;
        this.hand = hand;
        this.row = row;
    }

    @Override
    public void apply(Row row) {
        if (row != this.row){
            hand.getNCardsFromDeck(deck, 2);
        }
    }
}
