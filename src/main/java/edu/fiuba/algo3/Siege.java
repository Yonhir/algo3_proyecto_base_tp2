package edu.fiuba.algo3;

import java.util.List;

public class Siege extends RowType {
    @Override
    public void placeCardInRow(Row row, Card card, RowType selectedRowType) {
        if (!(selectedRowType instanceof Siege)) {
            return;
        }
        row.placeCard(card);
    }

    @Override
    public int calculateTotalPoints(Row row, RowType rowType) {
        if (rowType instanceof Siege) {
            return row.calculateTotalPoints();
        }
        return 0;
    }

    @Override
    public void applyEffectOnOwnerRow(Row row, SpecialEffect specialEffect, RowType selectedRowType) {
        if (selectedRowType instanceof Siege) {
            row.applyEffectOnOwnerCards(specialEffect);
        }
    }

    @Override
    public void applyEffectOnOpponentRow(Row row, SpecialEffect specialEffect, RowType selectedRowType) {
        if (selectedRowType instanceof Siege) {
            row.applyEffectOnOpponentCards(specialEffect);
        }
    }

    @Override
    public List<RowType> getPlayableRowTypes() {
        return List.of(this);
    }
} 