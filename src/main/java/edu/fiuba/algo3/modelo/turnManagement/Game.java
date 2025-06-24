package edu.fiuba.algo3.modelo.turnManagement;

public class Game {
    private final Player player1;
    private final Player player2;
    private Round currentRound;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        startNewRound();
    }

    public void startNewRound() {
        currentRound = new Round(player1, player2);
    }

    public void passRound() {
        currentRound.passTurn(this);
    }

    public boolean gameFinished() {
        int totalRoundsPlayed = player1.getRoundsWon() + player2.getRoundsWon();
        return player1.hasWonGame() || player2.hasWonGame() || totalRoundsPlayed == 2;
    }

    public Player gameWinner() {
        if (!gameFinished()) {
            throw new IllegalStateException("Game is not over yet.");
        }
        return player1.chooseWinnerAgainst(player2);
    }

    public void clearBoard() {
        player1.discardAllRows();
        player2.discardAllRows();
    }

    public boolean bothPlayersWonARound() {
        return player1.getRoundsWon() == 1 && player2.getRoundsWon() == 1 && gameFinished();
    }
}

