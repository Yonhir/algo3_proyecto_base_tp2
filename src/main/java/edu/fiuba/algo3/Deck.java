package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Deck extends CardCollection implements SpecialEffectApplicable {
    public Deck(List<Card> cards) {
        super(cards);
    }

    public List<Card> retrieveNRandomCard(int n) {
        List<Card> randomCards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int randomIndex = (int) (Math.random() * this.cards.size());
            Card randomCard = this.cards.get(randomIndex);
            this.cards.remove(randomIndex);
            randomCards.add(randomCard);
        }
        return randomCards;
    }

    public Card retrieveRandomCard() {
        List<Card> randomCards = retrieveNRandomCard(1);
        return randomCards.get(0);
    }

    @Override
    public void applyEffectOnOwner(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOwnerDeck(this, selectedRowType);
    }

    @Override
    public void applyEffectOnOpponent(SpecialEffect specialEffect, RowType selectedRowType) {
        specialEffect.applyOnOpponentDeck(this, selectedRowType);
    }
}
