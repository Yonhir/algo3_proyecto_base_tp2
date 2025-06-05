package edu.fiuba.algo3;

import java.util.ArrayList;

public class Side {
    private final ArrayList<Row> rows;

    public Side() {
        rows = new ArrayList<>();
        rows.add(new Row(new Ranged()));
        rows.add(new Row(new CloseCombat()));
        rows.add(new Row(new Siege()));
    }

    public void placeCard(Card card, RowType row) {
        for (Row aRow: rows) {
            aRow.placeCardInRow(card, row);
        }
    }

    public int calculatedTotalPoints() {
        int totalPoints = 0;
        for (Row aRow: rows) {
            totalPoints = totalPoints + aRow.calculateTotalPoints();
        }
        return totalPoints;
    }

    public void clearSide(CardCollection discardPile) {
        for (Row aRow: rows) {
            aRow.clearRow(discardPile);
        }
    }

}
