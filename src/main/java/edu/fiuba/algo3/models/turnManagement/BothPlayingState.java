package edu.fiuba.algo3.models.turnManagement;

public class BothPlayingState implements RoundState {
    @Override
    public void playCard(Round round) {
        round.swapPlayers();
    }

    @Override
    public void passTurn(Round round, Game game) {
        round.setState(new OnePassedState());
        round.swapPlayers();
    }
}
