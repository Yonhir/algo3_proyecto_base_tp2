package edu.fiuba.algo3;

import java.util.List;
import java.util.Map;

public class Game {
    private static Game instance;
    private Phase activePhase;

    private Game() {
        this.activePhase = new RegistrationPhase();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public DeckBuilder getDeckBuilder() {
        return activePhase.getDeckBuilder();
    }

    public void registerPlayer(String name, Deck deck) {
        Phase nextPhase = activePhase.registerPlayer(name, deck);
        this.activePhase = nextPhase;
    }

    public List<Card> getCurrentPlayerHand() {
        return activePhase.getCurrentPlayerHand();
    }

    public void drawAndDiscard(Card cardToDiscard) {
        activePhase.drawAndDiscard(cardToDiscard);
    }

    public void confirmHand() {
        Phase nextPhase = activePhase.confirmHand();
        this.activePhase = nextPhase;
    }

    public void playCardFromHand(Card card, RowType selectedRowType) {
        Phase nextPhase = activePhase.playCardFromHand(card, selectedRowType);
        this.activePhase = nextPhase;
    }

    public void playerPassRound() {
        Phase nextPhase = activePhase.playerPassRound();
        this.activePhase = nextPhase;
    }

    public void resetGame() {
        this.activePhase = new RegistrationPhase();
    }

    public Map<String, Object> getRoundsResults() {
        return activePhase.getRoundsResults();
    }
}
