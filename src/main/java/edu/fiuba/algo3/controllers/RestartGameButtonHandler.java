package edu.fiuba.algo3.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RestartGameButtonHandler implements EventHandler<ActionEvent> {
    
    private final AppController appController;

    public RestartGameButtonHandler(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void handle(ActionEvent event) {
        appController.restartGame();
    }
} 