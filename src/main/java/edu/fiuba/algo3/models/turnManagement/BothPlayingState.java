package edu.fiuba.algo3.models.turnManagement;

public class BothPlayingState implements RoundState {
    @Override
    public void playCard(Round round) {
        round.swapPlayers();
    }

    @Override
    public void passTurn(Round round, Game game) {
        Player passer = round.getCurrentPlayer();
        round.swapPlayers();
        round.setState(new OnePassedState(passer));
    }
}
