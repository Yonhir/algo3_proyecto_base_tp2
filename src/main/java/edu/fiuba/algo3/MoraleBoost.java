package edu.fiuba.algo3;

import java.util.List;

public class MoraleBoost extends Special {
 
    public MoraleBoost(String name, String description) {
        super(name, description);
    }
    
    @Override
    public List<RowType> getPlayableRowTypes() {
        return List.of(new CloseCombat(), new Ranged(), new Siege());
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
