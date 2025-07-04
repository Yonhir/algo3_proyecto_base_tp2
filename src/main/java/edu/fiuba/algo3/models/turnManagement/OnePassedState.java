package edu.fiuba.algo3.models.turnManagement;

public class OnePassedState implements RoundState {
    private final Player passedPlayer;

    public OnePassedState(Player passedPlayer) {
        this.passedPlayer = passedPlayer;
    }

    @Override
    public void playCard(Round round) {
        round.swapPlayers();
    }

    @Override
    public void passTurn(Round round, Game game) {
        round.setState(new BothPassedState());
        round.assignVictory();
        game.clearBoard();
        if (!game.gameFinished()) {
            game.startNewRound();
        }
    }
}
