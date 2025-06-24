package edu.fiuba.algo3.modelo.turnManagement;

public class Round {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player opponentPlayer;
    private RoundState state;

    public Round(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.opponentPlayer = player2;
        this.state = new BothPlayingState();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return opponentPlayer;
    }

    public void setState(RoundState state) {
        this.state = state;
    }

    public void playerPlayedCard() {
        state.playCard(this);
    }

    public void passTurn(Game game) {
        state.passTurn(this, game);
    }

    public void assignVictory() {
        player1.assignRoundVictoryToBetterPlayer(player2);
    }

    public void swapPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
    }

    public RoundState getState() {
        return this.state;
    }

}
