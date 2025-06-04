package edu.fiuba.algo3;

import java.util.ArrayList;

public class Row {
    private final CardCollection cards;
    private final RowType type;

    public Row(RowType rowType) {
        type = rowType;
        cards = new CardCollection();
    }

    public void placeCard(Card card, RowType row) {
        type.placeCardInRow(this, card, row);
    }

    protected void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculatePoints() {
        int puntaje_fila = 0;
        for(Card card: cards.getCards()){
            if (card.getClass() == Unit.class){
                puntaje_fila = puntaje_fila + ((Unit) card).getPuntos();
            }
        }
        return puntaje_fila;
    }

    public ArrayList<Card> clean() {
        ArrayList<Card> cards_to_clean = new ArrayList<>(cards.getCards());
        cards.removeAllCards();
        return cards_to_clean;
    }

}