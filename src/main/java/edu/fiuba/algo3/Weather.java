package edu.fiuba.algo3;

import java.util.List;

public class Weather extends Special {
    protected RowType affectedRow;

    public Weather(String name, String description, RowType affectedRow) {
        super(name, description);
        this.affectedRow = affectedRow;
    }

    @Override
    public List<RowType> getPlayableRowTypes() {
        return List.of(affectedRow);
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOwnerCard(this);
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOpponentCard(this);
    }

    @Override
    public void applyOnOwnerRow(Row row, RowType selectedRowType) {
        row.applyEffectOnOwnerRow(this, affectedRow);
    }

    @Override
    public void applyOnOpponentRow(Row row, RowType selectedRowType) {
        row.applyEffectOnOpponentRow(this, affectedRow);
    }

    @Override
    public void applyOnOwnerCard(Card card) {
        if (card instanceof Unit) {
            Unit unit = (Unit) card;
            unit.removeModifier(new WeatherModifier());
            unit.addModifier(new WeatherModifier());
        }
    }

    @Override
    public void applyOnOpponentCard(Card card) {
        if (card instanceof Unit) {
            Unit unit = (Unit) card;
            unit.removeModifier(new WeatherModifier());
            unit.addModifier(new WeatherModifier());
        }
    }

    private static class WeatherModifier implements PointModifier {
        @Override
        public int modifyPoints(int currentPoints) {
            return 1;
        }
    }
}
