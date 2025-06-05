package edu.fiuba.algo3;

import java.util.List;

public abstract class RowType {
    public abstract void placeCardInRow(Row row, Card card, RowType selectedRowType);
    public abstract int calculateTotalPoints(Row row, RowType rowType);
    public abstract void applyEffectOnOwnerRow(Row row, SpecialEffect specialEffect, RowType selectedRowType);
    public abstract void applyEffectOnOpponentRow(Row row, SpecialEffect specialEffect, RowType selectedRowType);
    public abstract List<RowType> getPlayableRowTypes();
}
