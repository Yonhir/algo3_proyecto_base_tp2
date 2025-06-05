package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Side {

    private final List<Row> rows;
    private final Row closeCombatRow;
    private final Row rangedRow;
    private final Row siegeRow;

    public Side() {
        this.closeCombatRow = new Row(new CloseCombat());
        this.rangedRow = new Row(new Ranged());
        this.siegeRow = new Row(new Siege());

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

    public int calculateTotalPointsForRow(RowType selectedRowType) {
        for (Row row : rows) {
            if (row.getRowType().getClass().equals(selectedRowType.getClass())) {
                return row.calculateTotalPoints();
            }
        }
        throw new IllegalArgumentException("RowType desconocido");
    }

}