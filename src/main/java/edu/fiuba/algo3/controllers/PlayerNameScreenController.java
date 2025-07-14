package edu.fiuba.algo3.controllers;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class PlayerNameScreenController implements EventHandler<ActionEvent> {
    private Runnable continueAction;

    public PlayerNameScreenController(Runnable onContinue){
        this.continueAction = onContinue;
    }

    @Override
    public void handle(ActionEvent event) {
        continueAction.run();
    }
}
