package edu.fiuba.algo3;

public class Ranged extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!(selectedRowType instanceof Ranged)) {
            return;
        }
        row.placeCard(card);
    }
}
