package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Side {

    private final List<Row> rows;

    public Side() {
        Row closeCombatRow = new Row(new CloseCombat());
        Row rangedRow = new Row(new Ranged());
        Row siegeRow = new Row(new Siege());

        this.rows = new ArrayList<>();
        this.rows.add(closeCombatRow);
        this.rows.add(rangedRow);
        this.rows.add(siegeRow);
    }

    public void placeCard(Card card, RowType selectedRowType) {
        for (Row row : rows) {
            row.placeCard(card, selectedRowType);
        }
    }

    public int calculateTotalPointsForRow(RowType rowType) {
        int totalPoints = 0;
        for (Row row : rows) {
            totalPoints += rowType.calculateTotalPoints(row, rowType);
        }
        return totalPoints;
    }

}