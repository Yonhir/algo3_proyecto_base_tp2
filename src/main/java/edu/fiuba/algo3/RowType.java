package edu.fiuba.algo3;

public abstract class RowType {
    public abstract void placeCardInRow(Row row, Card card, RowType selectedRowType);
    public abstract int calculateTotalPoints(Row row, RowType rowType);
}
