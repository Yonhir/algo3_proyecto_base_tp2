package edu.fiuba.algo3.modelo.turnManagement;

public interface RoundState {
    void playCard(Round round);
    void passTurn(Round round, Game game);
}
