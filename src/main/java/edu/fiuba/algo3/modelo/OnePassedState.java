package edu.fiuba.algo3.modelo;

public class OnePassedState implements RoundState{
    private final Player passedPlayer;

    public OnePassedState(Player passedPlayer) {
        this.passedPlayer = passedPlayer;
    }

    @Override
    public void playCard(Round round) {
        round.swapPlayers();
    }

    @Override
    public void passTurn(Round round) {
        round.setState(new BothPassedState());
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
