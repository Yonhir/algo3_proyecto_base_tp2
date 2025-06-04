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

    public int calculatedPoints() {
        int totalPoints = 0;
        for (Row aRow: rows) {
            totalPoints = totalPoints + aRow.calculatePoints();
        }
        return totalPoints;
    }

    public ArrayList<Card> clean() {
        ArrayList<Card> cards_to_clean = new ArrayList<>();
        for (Row aRow: rows) {
            cards_to_clean.addAll(aRow.clean());
        }
        return cards_to_clean;
    }

}
