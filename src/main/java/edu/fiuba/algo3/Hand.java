package edu.fiuba.algo3;

import java.util.List;

public class Hand extends CardCollection implements SpecialEffectApplicable {
    public Hand(List<Card> cards) {
        super(cards);
    }

    public boolean isEmpty() {
        return this.getCards().isEmpty();
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOwnerHand(this, selectedRowType);
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOpponentHand(this, selectedRowType);
    }
}
