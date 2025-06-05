package edu.fiuba.algo3;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class RegistrationPhase implements Phase {
    private Player player1;
    private Player player2;

    public RegistrationPhase() {
    }

    @Override
    public Phase registerPlayer(String name, Deck deck) {
        if (player1 == null) {
            player1 = new Player(name, 2, new Hand(new ArrayList<Card>()), new DiscardPile(new ArrayList<Card>()), deck, new Side());
        } else if (player2 == null) {
            player2 = new Player(name, 2, new Hand(new ArrayList<Card>()), new DiscardPile(new ArrayList<Card>()), deck, new Side());
            return new PreparationPhase(player1, player2);
        } 
        return this;
    }

    @Override
    public Phase playCardFromHand(Card card, RowType selectedRowType) {
        throw new IllegalStateException("Cannot play cards during registration phase");
    }

    @Override
    public Phase drawAndDiscard(Card cardToDiscard) {
        throw new IllegalStateException("Cannot draw and discard during registration phase");
    }

    @Override
    public Phase confirmHand() {
        throw new IllegalStateException("Cannot confirm hand during registration phase");
    }

    @Override
    public Phase playerPassRound() {
        throw new IllegalStateException("Cannot pass round during registration phase");
    }

    @Override
    public DeckBuilder getDeckBuilder() {
        return new DeckBuilder();
    }

    @Override
    public List<Card> getCurrentPlayerHand() {
        throw new IllegalStateException("No player hand available during registration phase");
    }

    @Override
    public Map<String, Object> getRoundsResults() {
        throw new IllegalStateException("No rounds results available during registration phase");
    }
} 