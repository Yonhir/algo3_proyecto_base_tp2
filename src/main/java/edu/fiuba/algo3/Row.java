package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Row implements SpecialEffectApplicable {
    private List<Card> cards;
    private RowType rowType;

    public Row(RowType rowType) {
        this.cards = new ArrayList<>();
        this.rowType = rowType;
    }

    public void placeCard(Card card, RowType selectedRowType) {
        rowType.placeCardInRow(this, card, selectedRowType);
    }

    public void placeCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalPoints(RowType rowType) {
        int total = 0;
        for (Card card : cards) {
            if (card instanceof Unit) {
                total += ((Unit) card).getPoints();
            }
        }
        return total;
    }

    public int calculateTotalPoints() {
        int total = 0;
        for (Card card : cards) {
            if (card instanceof Unit) {
                total += ((Unit) card).getPoints();
            }
        }
        return total;
    }

    public void clear(DiscardPile discardPile) {
        for (Card card : cards) {
            discardPile.addCard(card);
        }
        cards.clear();
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOwnerRow(this, selectedRowType);
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOpponentRow(this, selectedRowType);
    }

    public void applyEffectOnOwnerRow(SpecialEffect specialEffect, RowType selectedRowType) {
        rowType.applyEffectOnOwnerRow(this, specialEffect, selectedRowType);
    }

    public void applyEffectOnOpponentRow(SpecialEffect specialEffect, RowType selectedRowType) {
        rowType.applyEffectOnOpponentRow(this, specialEffect, selectedRowType);
    }

    public void applyEffectOnOwnerCards(SpecialEffect specialEffect) {
        for (Card card : cards) {
            specialEffect.applyOnOwnerCard(card);
        }
    }

    public void applyEffectOnOpponentCards(SpecialEffect specialEffect) {
        for (Card card : cards) {
            specialEffect.applyOnOpponentCard(card);
        }
    }
}
