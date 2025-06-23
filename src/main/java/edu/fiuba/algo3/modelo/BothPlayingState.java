package edu.fiuba.algo3.modelo;

public class BothPlayingState implements RoundState{
    @Override
    public void playCard(Round round) {
        round.swapPlayers();
    }

    @Override
    public void passTurn(Round round) {
        round.setState(new OnePassedState(round.getOpponent()));
        round.swapPlayers();
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
