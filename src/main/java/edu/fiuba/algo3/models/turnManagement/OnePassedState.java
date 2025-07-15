package edu.fiuba.algo3.models.turnManagement;

public class OnePassedState implements RoundState {
    private final Player passedPlayer;

    public OnePassedState(Player passedPlayer) {
        this.passedPlayer = passedPlayer;
    }

    @Override
    public void playCard(Round round) {
        Player current = round.getCurrentPlayer();
        if (current.equals(passedPlayer)) {
            throw new IllegalStateException("Ya pasaste, no puedes jugar más cartas en esta ronda.");
        }
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
