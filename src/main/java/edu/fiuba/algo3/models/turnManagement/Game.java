package edu.fiuba.algo3.models.turnManagement;

import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.sections.SpecialZone;

public class Game {
    private final Player player1;
    private final Player player2;
    private final SpecialZone specialZone;
    private Round currentRound;

    public Game(Player player1, Player player2, SpecialZone specialZone) {
        this.player1 = player1;
        this.player2 = player2;
        this.specialZone = specialZone;

        startNewRound();
    }

    public void startNewRound() {
        currentRound = new Round(player1, player2);
    }

    public void passRound() {
        currentRound.passTurn(this);
    }

    public boolean gameFinished() {
        return player1.hasWonGame() || player2.hasWonGame();
    }

    public Player gameWinner() {
        if (!gameFinished()) {
            throw new IllegalStateException("Game is not over yet.");
        }
        return player1.chooseWinnerAgainst(player2);
    }

    public void clearBoard() {
        specialZone.clearZone();
        player1.discardAllRows();
        player2.discardAllRows();
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public Player getCurrentPlayer() {
        return this.getCurrentRound().getCurrentPlayer();
    }

    public Player getOpponentPlayer() {
        return this.getCurrentRound().getOpponent();
    }
}

