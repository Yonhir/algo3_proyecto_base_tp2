package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Side implements SpecialEffectApplicable {
    private final List<Row> rows;

    public Side() {
        this.rows = new ArrayList<>();
        this.rows.add(new Row(new CloseCombat()));
        this.rows.add(new Row(new Ranged()));
        this.rows.add(new Row(new Siege()));
    }

    public void placeCard(Card card, RowType selectedRowType) {
        for (Row row : rows) {
            row.placeCard(card, selectedRowType);
        }
    }

    public int calculateTotalPoints() {
        int totalPoints = 0;
        for (Row row : rows) {
            totalPoints += row.calculateTotalPoints();
        }
        return totalPoints; 
    }

    public int calculateTotalPointsForRow(RowType rowType) {
        int totalPoints = 0;
        for (Row row : rows) {
            totalPoints += rowType.calculateTotalPoints(row, rowType);
        }
        return totalPoints;
    }

    public void clearSide(DiscardPile discardPile) {
        for (Row row : rows) {
            row.clear(discardPile);
        }
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        for (Row row : rows) {
            row.applyEffectOnOwner(specialEffect, selectedRowType);
        }
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        for (Row row : rows) {
            row.applyEffectOnOpponent(specialEffect, selectedRowType);
        }
    }
}
