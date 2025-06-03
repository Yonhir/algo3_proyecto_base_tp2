package edu.fiuba.algo3;

public class CloseCombat extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card) {
        row.placeCard(card);
    }
}
