package edu.fiuba.algo3;

import java.util.List;

public class Unit extends Card {
    private final int basePoint;
    private final RowType rowType;
    private final EffectManager effectManager;
    private List<Modifier> modifiers;

    public Unit(String name, String description, int basePoint, RowType rowType, List<Modifier> modifiers) {
        super(name, description);
        this.basePoint = basePoint;
        this.rowType = rowType;
        this.effectManager = new EffectManager();
        this.modifiers = modifiers;
    }

    @Override
    public List<RowType> getPlayableRowTypes() {
        return rowType.getPlayableRowTypes();
    }

    public int getPoints() {
        return effectManager.calculateTotalEffect(basePoint);
    }

    public void addModifier(PointModifier modifier) {
        effectManager.addModifier(modifier);
    }

    public void removeModifier(PointModifier modifier) {
        effectManager.removeModifier(modifier);
    }

    public void clearEffects() {
        effectManager.clearEffects();
    }

    @Override
    public void play(Player owner, Player opponent, RowType selectedRowType) {
        owner.playCard(this, selectedRowType);
        applyModifiers(owner, opponent, selectedRowType);
    }

    private void applyModifiers(Player owner, Player opponent, RowType selectedRowType) {
        for (Modifier modifier : modifiers) {
            modifier.applyOnOwnerPlayer(owner, selectedRowType);
            modifier.applyOnOpponentPlayer(opponent, selectedRowType);
        }
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
