package edu.fiuba.algo3;

public class Ranged extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!(selectedRowType instanceof Ranged)) {
            return;
        }
        row.placeCard(card);
    }

    @Override
    public int calculateTotalPoints(Row row, RowType rowType) {
        if (rowType instanceof Ranged) {
            return row.calculateTotalPoints();
        }
        return 0;
    }
}
