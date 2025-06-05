package edu.fiuba.algo3;

public class CloseCombat extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!(selectedRowType instanceof CloseCombat)) {
            return;
        }
        row.placeCard(card);
    }

    @Override
    public int calculateTotalPoints(Row row, RowType rowType) {
        if (rowType instanceof CloseCombat) {
            return row.calculateTotalPoints();
        }
        return 0;
    }
}

