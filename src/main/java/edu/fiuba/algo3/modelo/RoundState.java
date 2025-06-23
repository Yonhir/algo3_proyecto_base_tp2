package edu.fiuba.algo3.modelo;

public interface RoundState {
    void playCard(Round round);
    void passTurn(Round round);
    boolean isOver();
}
