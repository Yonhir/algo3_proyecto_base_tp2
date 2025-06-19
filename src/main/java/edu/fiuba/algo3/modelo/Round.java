package edu.fiuba.algo3.modelo;

public class Round {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player opponentPlayer;

    public Round(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.opponentPlayer = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void endTurn() {
        if (!opponentPlayer.hasPassed()) {
            swapPlayers();
        }
    }

    public void passTurn() {
        currentPlayer.passRound();
        endTurn();
    }

    public boolean isOver() {
        return player1.hasPassed() && player2.hasPassed();
    }

    public Player getWinner() {
        int points1 = player1.calculatePoints();
        int points2 = player2.calculatePoints();
        return (points1 >= points2) ? player1 : player2;
    }

    public void assignVictory() {
        getWinner().winRound();
    }

    private void swapPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
    }
}
