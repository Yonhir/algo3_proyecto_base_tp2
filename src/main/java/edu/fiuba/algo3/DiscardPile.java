package edu.fiuba.algo3;

import java.util.List;

public class DiscardPile extends CardCollection implements SpecialEffectApplicable {
    public DiscardPile(List<Card> cards) {
        super(cards);
    }

    public Card getLastCard() {
        List<Card> cards = this.getCards();
        if (cards.isEmpty()) return null;
        return cards.get(cards.size() - 1);
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOwnerDiscardPile(this, selectedRowType);
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOpponentDiscardPile(this, selectedRowType);
    }
} 