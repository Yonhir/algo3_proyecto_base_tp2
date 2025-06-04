package edu.fiuba.algo3;

public class Siege extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType type){
        if (this.getClass() != type.getClass()) {
            return;
        }
        row.addCard(card);
    }
}