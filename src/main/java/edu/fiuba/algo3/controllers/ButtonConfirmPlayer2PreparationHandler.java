package edu.fiuba.algo3.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonConfirmPlayer2PreparationHandler implements EventHandler<ActionEvent> {
    private final AppController appController;

    public ButtonConfirmPlayer2PreparationHandler(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void handle(ActionEvent event) {
        appController.loadGameView();
    }
} 