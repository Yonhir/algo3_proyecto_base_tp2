package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Card> cards;
    private RowType rowType;

    public Row(RowType rowType) {
        this.cards = new ArrayList<>();
        this.rowType = rowType;
    }

    public void placeCard(Card card) {
        cards.add(card);
    }

    public void placeCard(Card card, RowType selectedRowType) {
        rowType.placeCardInRow(this, card, selectedRowType);
    }


    public List<Card> getCards() {
        return cards;
    }

    public int calculateTotalPoints() {
        int total = 0;
        for (Card card : cards) {
            if (card instanceof Unit) {
                total += ((Unit) card).getPoints();
            }
        }
        return total;
    }



}