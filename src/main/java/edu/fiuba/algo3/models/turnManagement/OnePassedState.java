package edu.fiuba.algo3.models.turnManagement;

public class OnePassedState implements RoundState {

    @Override
    public void playCard(Round round) {
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
