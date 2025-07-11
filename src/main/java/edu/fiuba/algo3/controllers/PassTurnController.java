package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.turnManagement.Game;

public class PassTurnController {

    public void passTurn(Game game) {
        game.passRound();
    }
}
