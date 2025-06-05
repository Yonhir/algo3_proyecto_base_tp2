package edu.fiuba.algo3;

import java.util.ArrayList;

public class Row {
    private final ArrayList<Card> cards;
    private final RowType type;

    public Row(RowType rowType) {
        type = rowType;
        cards = new ArrayList<>();
    }

    public void placeCardInRow(Card card, RowType row) {
        type.placeCardInRow(this, card, row);
    }

    protected void placeCard(Card card) {
        cards.add(card);
    }

    public void clearRow(CardCollection discardPile) {
        ArrayList<Card> cards_to_clean = new ArrayList<>(cards);
        discardPile.addCards(cards_to_clean);
        cards.clear();
    }

}