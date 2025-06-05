package edu.fiuba.algo3;

public class CloseCombat extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!(selectedRowType instanceof CloseCombat)) {
            return;
        }
        row.placeCard(card);
    }
}

