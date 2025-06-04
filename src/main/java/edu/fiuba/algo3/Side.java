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
            aRow.placeCard(card, row);
        }
    }

    public void clearSide(CardCollection discardPile) {
        for (Row aRow: rows) {
            aRow.clearRow(discardPile);
        }
    }

}