package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.sections.SpecialZone;

public class Game {
    private final Player player1;
    private final Player player2;
    private Round currentRound;
    private final SpecialZone specialZone;

    public Game(Player player1, Player player2, SpecialZone specialZone) {
        this.player1 = player1;
        this.player2 = player2;
        this.specialZone = specialZone;
        startNewRound();
    }

    public void startNewRound() {
        player1.resetPass();
        player2.resetPass();
        currentRound = new Round(player1, player2);
    }

    public void passTurn() {
        currentRound.passTurn();
        checkRoundEnd();
    }

    public boolean isGameOver() {
        int totalRoundsPlayed = player1.getRoundsWon() + player2.getRoundsWon();
        return player1.hasWonGame() || player2.hasWonGame() || totalRoundsPlayed == 2;
    }

    public Player getGameWinner() {
        if (!isGameOver()) {
            throw new IllegalStateException("Game is not over yet.");
        }
        return player1.hasWonGame() ? player1 : player2;
    }

    private void checkRoundEnd() {
        if (currentRound.isOver()) {
            currentRound.assignVictory();
            clearBoard();
            if (!isGameOver()) {
                startNewRound();
            }
        }
    }

    private void clearBoard() {
        player1.discardAllRows();
        player2.discardAllRows();
    }

    public boolean isDraw() {
        return player1.getRoundsWon() == 1 && player2.getRoundsWon() == 1 && isGameOver();
    }
}

