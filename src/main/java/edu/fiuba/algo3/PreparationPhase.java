package edu.fiuba.algo3;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class PreparationPhase implements Phase {
    private Player currentPlayer;
    private Player opponentPlayer;
    private boolean playerHasConfirmedHand;
    private int drawAndDiscardCount;

    public PreparationPhase(Player player1, Player player2) {
        this.currentPlayer = player1;
        this.opponentPlayer = player2;
        this.playerHasConfirmedHand = false;
        this.currentPlayer.drawNCards(10);
        this.opponentPlayer.drawNCards(10);
        this.drawAndDiscardCount = 0;
    }

    @Override
    public DeckBuilder getDeckBuilder() {
        throw new IllegalStateException("Cannot get deck builder during preparation phase");
    }

    @Override
    public Phase registerPlayer(String name, Deck deck) {
        throw new IllegalStateException("Cannot register player during preparation phase");
    }

    @Override
    public List<Card> getCurrentPlayerHand() {
        return currentPlayer.getHand();
    }

    @Override
    public Phase drawAndDiscard(Card cardToDiscard) {
        currentPlayer.drawAndDiscard(cardToDiscard);
        drawAndDiscardCount++;
        if (drawAndDiscardCount == 2) {
            drawAndDiscardCount = 0;
            return playerConfirmHand();
        }
        return this;
    }

    @Override
    public Phase confirmHand() {
        currentPlayer.confirmHand();
        return playerConfirmHand();
    }

    @Override
    public Phase playCardFromHand(Card card, RowType selectedRowType) {
        throw new IllegalStateException("Cannot play cards during preparation phase");
    }

    @Override
    public Phase playerPassRound() {
        throw new IllegalStateException("Cannot pass round during preparation phase");
    }

    @Override
    public Map<String, Object> getRoundsResults() {
        throw new IllegalStateException("No rounds results available during preparation phase");
    }

    private Phase playerConfirmHand() {
        if (playerHasConfirmedHand) {
            return endPhase();
        }
        playerHasConfirmedHand = true;
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
        return this;
    }

    private Round endPhase() {
        return new Round(opponentPlayer, currentPlayer, 1, new ArrayList<Round>());
    }
}
