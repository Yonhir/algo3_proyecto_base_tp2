package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.colors.PlayerColor;
import edu.fiuba.algo3.modelo.sections.rows.Row;

public class Spy implements Modifier {
    private Deck deck;
    private Hand hand;
    public Spy(Deck deck, Hand hand) {
        this.deck = deck;
        this.hand = hand;
    }

    @Override
    public void setColor(Unit unit, PlayerColor playerColor){
        unit.setColor(playerColor.swapColor());
    }

    @Override
    public void apply(Row row) {
        hand.getNCardsFromDeck(deck, 2);
    }
}
