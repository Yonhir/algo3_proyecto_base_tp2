package edu.fiuba.algo3;

public interface SpecialEffectApplicable {
    void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType);
    void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType);
}
