package edu.fiuba.algo3;

import java.util.List;

public class ClearWeather extends Special {
    public ClearWeather() {
        super("Clear Weather", "Removes all Weather Card (Biting Frost, Impenetrable Fog and Torrential Rain) effects.");
    }

    @Override
    public List<RowType> getPlayableRowTypes() {
        return List.of();
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOwnerCard(this);
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOpponentCard(this);
    }
}
