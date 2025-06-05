package edu.fiuba.algo3;

public class Siege extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!(selectedRowType instanceof Siege)) {
            return;
        }
        row.placeCard(card);
    }

    @Override
    public int calculateTotalPoints(Row row, RowType rowType) {
        if (rowType instanceof Siege) {
            return row.calculateTotalPoints();
        }
        return 0;
    }
}
