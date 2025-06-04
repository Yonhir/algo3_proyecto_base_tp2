package edu.fiuba.algo3;

public class Siege extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType type){
        if (card.getClass() == Unit.class){
            if(((Unit) card).sameType(type) && this.getClass() == type.getClass()){
                row.addCard(card);
            }
        }
        //clase especial
    }
}