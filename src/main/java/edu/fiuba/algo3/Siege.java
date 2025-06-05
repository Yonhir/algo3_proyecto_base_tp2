package edu.fiuba.algo3;

public class Siege extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!selectedRowType.getClass().equals(this.getClass())) {
            return;
        }
        row.placeCard(card);
    }
}