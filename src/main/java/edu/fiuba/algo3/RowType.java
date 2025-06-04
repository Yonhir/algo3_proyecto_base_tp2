package edu.fiuba.algo3;

public abstract class RowType {
    public void placeCardInRow(Row row, Card card, RowType type){
        if (card.getClass() == Unit.class){
            if(((Unit) card).sameType(type) && this.getClass() == type.getClass()){
                row.addCard(card);
            }
        }
        //clase especial
    }
}
