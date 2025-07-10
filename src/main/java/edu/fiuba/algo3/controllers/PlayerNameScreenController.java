package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.PlayerNameScreenView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class PlayerNameScreenController implements EventHandler<ActionEvent> {
    private PlayerNameScreenView.ContinueGame callback;

    public PlayerNameScreenController(PlayerNameScreenView.ContinueGame continueGame) {
        callback = continueGame;
    }

    @Override
    public void handle(ActionEvent event) {
        callback.continueGame();
    }
}
