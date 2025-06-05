package edu.fiuba.algo3;

public class Siege extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!(selectedRowType instanceof Siege)) {
            return;
        }
        row.placeCard(card);
    }
}
