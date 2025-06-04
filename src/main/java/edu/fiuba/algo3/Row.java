package edu.fiuba.algo3;

import java.util.ArrayList;

public class Row {
    private final ArrayList<Card> cards;
    private final RowType type;

    public Row(RowType rowType) {
        type = rowType;
        cards = new ArrayList<>();
    }

    public void placeCard(Card card, RowType row) {
        type.placeCardInRow(this, card, row);
    }

    protected void addCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalPoints() {
        int puntaje_fila = 0;
        for(Card card: cards){
            puntaje_fila = puntaje_fila + ((Unit) card).getPoints();
        }
        return puntaje_fila;
    }

    public void clearRow(CardCollection discardPile) {
        ArrayList<Card> cards_to_clean = new ArrayList<>(cards);
        discardPile.addCards(cards_to_clean);
        cards.clear();
    }

}