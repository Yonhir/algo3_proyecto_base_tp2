package edu.fiuba.algo3.models.turnManagement;

public interface RoundState {
    void playCard(Round round);
    void passTurn(Round round, Game game);
}
