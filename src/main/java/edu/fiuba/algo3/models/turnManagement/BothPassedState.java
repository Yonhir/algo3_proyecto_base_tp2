package edu.fiuba.algo3.models.turnManagement;

public class BothPassedState implements RoundState {
    @Override
    public void playCard(Round round) {
        throw new IllegalStateException("La ronda ha terminado.");
    }

    @Override
    public void passTurn(Round round, Game game) {
        throw new IllegalStateException("La ronda ha terminado.");
    }
}
