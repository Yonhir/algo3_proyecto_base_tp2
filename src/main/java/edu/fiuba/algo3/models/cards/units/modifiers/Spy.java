package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.sections.rows.Row;

public class Spy implements Modifier {
    private final Deck deck;
    private final Hand hand;
    
    public Spy(Deck deck, Hand hand) {
        this.deck = deck;
        this.hand = hand;
    }

    @Override
    public void apply(Row row) {
        hand.getNCardsFromDeck(deck, 2);
    }
    
    @Override
    public String getDescription() {
        return "Espía: Roba 2 cartas del mazo y se coloca en el lado del oponente";
    }
}
