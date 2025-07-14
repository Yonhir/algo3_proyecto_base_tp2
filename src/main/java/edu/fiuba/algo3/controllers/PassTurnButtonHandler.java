package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.turnManagement.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PassTurnButtonHandler implements EventHandler<ActionEvent> {
    private final Game game;

    public PassTurnButtonHandler(Game game) {
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        game.passRound();
    }
}
